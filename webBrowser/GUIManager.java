
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import javax.swing.*;


public class GUIManager 
{
    //attributes
    JFrame fMain;
    JEditorPane jepMain;
    JButton btnGo, b1,b2;
    JTextField tfAddress;
    String strSearch;
    JMenuBar jmb;
    JMenu[] jm;
    JMenuItem[] jmi;
    JButton[] jb;
    Stack<String>  s;
    Stack<String>  s1;
    JPopupMenu menu;
    JPanel[] jpanel;
    actionListener hnd;
    JTextArea textArea;
    
    //constructor
    public GUIManager() {
        initGui();
    }
    
    private void initGui()
    {
        String[] txt = {"Navigator","Home page","Tools","Set"};
        String[] link = {"img/right.png","img/left.png","img/home.png","img/reload.png","img/history.png","img/favorite.png","img/search.png"};
        String[] jmitxt = {"Next","Perivous","Go To Home Page","ReSet","Refresh","Search","Add Favorite","View Favorite","View History","Set FireWall","Set HomePage"};
        GridBagLayout g = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints dc = new GridBagConstraints();
        GridBagConstraints fc = new GridBagConstraints();
        fMain = new JFrame();
        menu = new JPopupMenu();
        b1= new JButton("Add Favorite");
        b2 = new JButton("View Favorite");
        jepMain = new JEditorPane();
        jmb = new JMenuBar();
        jm = new JMenu[20];
        jb = new JButton[20];
        jmi =new JMenuItem[20];
        s = new Stack<String>();
        s1 = new Stack<String>();
        jpanel = new JPanel[10];
        hnd = new actionListener(this);
        textArea = new JTextArea();
        
        for (int i = 0; i < 7; i++)
        {
             jb[i] = new JButton();
             jb[i].setActionCommand(Integer.toString(i));
             jmi[i] = new JMenuItem();
             jpanel[i] = new JPanel();
             jb[i].setIcon(new ImageIcon(link[i]));
             jb[i].addActionListener(hnd);
             jb[i].setPreferredSize(new Dimension(194, 60));
             jpanel[i].setLayout(g);
        }
        for (int i = 0; i < 4; i++)
        {
             jm[i] =  new JMenu();
             jm[i].setText(txt[i]);
             jm[i].setPreferredSize(new Dimension(194, 30));
             jm[i].setForeground(Color.BLUE);
        }
        for (int i = 0; i < 11; i++)
        {
            jmi[i] = new JMenuItem();
            jmi[i].addActionListener(hnd);
            jmi[i].setText(jmitxt[i]);
            jmi[i].setForeground(Color.BLUE);
        }
        jm[0].add(jmi[0]);
        jm[0].add(jmi[1]);
        jm[1].add(jmi[2]);
        jm[1].add(jmi[3]);
        jm[2].add(jmi[4]);
        jm[2].add(jmi[5]);
        jm[2].add(jmi[6]);
        jm[2].add(jmi[7]);
        jm[2].add(jmi[8]);
        jm[3].add(jmi[9]);
        jm[3].add(jmi[10]);
        jmi[0].setEnabled(false);
        jmi[1].setEnabled(false);
        
        tfAddress = new JTextField();
        btnGo = new JButton("GO");
        fMain.setLayout(new BorderLayout());
        jepMain.setPreferredSize(new Dimension(1360, 600));
        tfAddress.setPreferredSize(new Dimension(1160, 30));
        btnGo.setPreferredSize(new Dimension(200, 20));
        jb[0].setEnabled(false);
        jb[1].setEnabled(false);
        btnGo.addActionListener(hnd);
        jepMain.addHyperlinkListener(hnd);
        
        c.fill = GridBagConstraints.BOTH;
        dc.fill = GridBagConstraints.BOTH;
        fc.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.gridx = 0;
        dc.gridy=0;
        fc.gridx = 0;
        fc.gridy = 0;
        fc.gridwidth = 6;
        jpanel[0].add(jmb);
        for (int i = 0; i < 4; i++) 
            jmb.add(jm[i]);
        for (int i = 0; i < 7; i++) {
            c.gridx= i;
            jpanel[1].add(jb[i],dc);
        }
        jpanel[2].add(tfAddress,fc);
        fc.gridx = 6;
        fc.gridwidth = 1;
        jpanel[2].add(btnGo,fc);
        jpanel[3].add(jpanel[0],c);
        c.gridy = 1;
        jpanel[3].add(jpanel[1],c);
        c.gridy = 2;
        jpanel[3].add(jpanel[2],c);
        fMain.add(jpanel[3],BorderLayout.NORTH);
        fMain.add(jepMain,BorderLayout.SOUTH);
        fMain.add(new JScrollPane(jepMain),BorderLayout.SOUTH);
        
        fMain.setResizable(false);
        fMain.setSize(900,900);
        fMain.setLocation(0,0);
        fMain.setVisible(true);
        fMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fMain.pack();
        jepMain.setEditable(false);
        
    }
    public void loadPage(String url){
        try{
        
            jepMain.setPage(url);
            tfAddress.setText(url);
        }
        catch(IOException ioexp){
            JOptionPane.showMessageDialog(null,"page not found","bad url",JOptionPane.ERROR_MESSAGE);    
        }
    }
   
    public void WriteFile(String x,String file)
    {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(new File(file),true));
            pr.println(x);
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void enabled()
    {
         File file = new File("history");
         if(s.size() > 1)
         {
             jb[1].setEnabled(true);
             jmi[1].setEnabled(true);
         }
         else
         {
             jb[1].setEnabled(false);
             jmi[1].setEnabled(false);
         }
         if(s1.size() > 0)
         {
             jb[0].setEnabled(true);
             jmi[0].setEnabled(true);
         }
         else
         {
             jb[0].setEnabled(false);
             jmi[0].setEnabled(false);
         }
    }
    public String read(String file)
    {
       String str = "";
       try { 
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            String line = br.readLine();
            int lineNum = 0;
            while(line!=null)
            {
                line = br.readLine();
                if(line!=null)
                str+=line;
                str+="\n";
                lineNum++;
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
    
}

        