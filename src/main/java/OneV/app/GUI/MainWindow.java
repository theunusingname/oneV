package OneV.app.GUI;

import OneV.app.CutLoaderImpl;
import OneV.app.CutsTimelineImpl;
import OneV.app.TimeLineDriverImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by kkuznetsov on 21.03.2016.
 */
public class MainWindow implements ActionListener {
    private CutLoaderImpl loader;
    private ToolbarPanel toolbarPanel;
    private MoviePreViewPanel moviePreViewPanel;
    private TimeLinePanel timeLinePanel;
    private JFrame frame;
    private JMenuBar mainMenubar;
    protected CutsTimelineImpl mainTimeLine=new CutsTimelineImpl();
    TimeLineDriverImpl driver;

    public MainWindow()
    {
        moviePreViewPanel=new MoviePreViewPanel(this);
        driver=new TimeLineDriverImpl(mainTimeLine,moviePreViewPanel.movieView);
        loader=new CutLoaderImpl(640,480);
        timeLinePanel=new TimeLinePanel(this);
        toolbarPanel =new ToolbarPanel(this);
        mainMenubar=new MainMenu(this);
        frame=new JFrame("OneV");
        frame.setJMenuBar(mainMenubar);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(toolbarPanel,BorderLayout.NORTH);
        frame.add(moviePreViewPanel,BorderLayout.CENTER);
        frame.add(timeLinePanel,BorderLayout.SOUTH);
        frame.setSize(800,600);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "load":
            case "Load":
                mainTimeLine.addContainer( loader.getCutWithDialog());
                break;
            case "start": driver.play(3);
                break;
            case "stop": driver.stop();
                break;
            default:
                System.out.println("Unknown command " +e.getActionCommand());

        }
    }
}
