package com.levi.basic.thread2;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class SwingWorkerTest {
    public static void main(String[] args) {
        //调度所有的UI更新在UI线程中执行，该机制可以用来调度一个Runnable在UI线程执行
        EventQueue.invokeLater(() -> {
            var frame = new SwingWorkerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class SwingWorkerFrame extends JFrame{
    private JFileChooser chooser;
    private JTextArea textArea;
    private JLabel statusLine;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private SwingWorker<StringBuilder, ProgressData> textReader;
    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 60;

    public SwingWorkerFrame(){
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(textArea));

        statusLine = new JLabel(" ");
        add(statusLine, BorderLayout.SOUTH);

        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        var menu = new JMenu("File");
        menuBar.add(menu);

        openItem = new JMenuItem("Open");
        menu.add(openItem);
        //点击触发事件
        openItem.addActionListener(event -> {
            //show file chooser dialog
            int result = chooser.showOpenDialog(null);

            //if file selected, set it as icon of the label
            if (result == JFileChooser.APPROVE_OPTION){
                textArea.setText("");
                openItem.setEnabled(false);
                textReader = new TextReader(chooser.getSelectedFile());
                textReader.execute();
                cancelItem.setEnabled(true);
            }
        });

        cancelItem = new JMenuItem("Cancel");
        menu.add(cancelItem);
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(event -> textReader.cancel(true));
        pack();

    }

    private class ProgressData{
        public int number;
        public String line;
    }

    /*
    * SwingWorker辅助类管理细节
    *   1.为长时间运行任务指定动作（在一个单独的线程中运行）
    *   2.指定进度更新以及最终的布局（在UI线程中进行）
    * */
    private class TextReader extends SwingWorker<StringBuilder, ProgressData>{
        private File file;
        private StringBuilder text = new StringBuilder();

        public TextReader(File file){
            this.file = file;
        }

        public StringBuilder doInBackground() throws IOException, InterruptedException {
            int lineNumber = 0;
            try (var in  = new Scanner(new FileInputStream(file), StandardCharsets.UTF_8)){
                while (in.hasNext()){
                    String line = in.nextLine();
                    lineNumber++;
                    text.append(line).append("\n");
                    var data = new ProgressData();
                    data.number = lineNumber;
                    data.line = line;
                    publish(data);
                    Thread.sleep(1);//为了测试取消任务
                }
            }

            return text;
        }

        public void process(List<ProgressData> data){
            if (isCancelled()) return;
            var builder = new StringBuilder();
            statusLine.setText("" + data.get(data.size() - 1).number);
            for (ProgressData d : data){
                builder.append(d.line).append("\n");
            }
            textArea.append(builder.toString());
        }

        public void done(){
            try {
                StringBuilder result = get();
                textArea.setText(result.toString());
                statusLine.setText("Done");
            }
            catch (InterruptedException e){

            }
            catch (CancellationException e){
                textArea.setText("");
                statusLine.setText("Cancelled");
            }
            catch (ExecutionException e){
                statusLine.setText("" + e.getCause());
            }

            cancelItem.setEnabled(false);
            openItem.setEnabled(true);

        }
    }
}
