
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


public class actionListener implements ActionListener,HyperlinkListener{

    GUIManager ref; 
    public actionListener(GUIManager r) 
    {
        ref = r;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
       
       if(e.getActionCommand().equals("GO"))
       {
            ref.loadPage(ref.tfAddress.getText());
            if(ref.tfAddress.getText() != "" )
            ref.WriteFile(ref.tfAddress.getText(),"history");
            ref.s.push(ref.tfAddress.getText());
            ref.enabled();
       }
       else if(e.getActionCommand().equals("Add Favorite"))
       {
            addf(ref.textArea);
       }
       else if(e.getActionCommand().equals("View Favorite"))
       {
           display(ref.read("Favorites"));
       }
       else if(e.getActionCommand().equals("0") || e.getActionCommand().equals("Next"))
        {
            if(ref.s1.size() > 0)
            {
                String str =  ref.s1.pop();
                ref.s.push(str);
                ref.loadPage(str);
                if(ref.tfAddress.getText() != "" )
                ref.WriteFile(ref.tfAddress.getText(),"history");
                ref.enabled(); 
            }
        }
        else if(e.getActionCommand().equals("1") || e.getActionCommand().equals("Perivous"))
        {
            if(ref.s.size() > 1)
            {
                String str =  ref.s.pop();
                ref.s1.push(str);
                str =  ref.s.pop();
                ref.s.push(str);
                ref.loadPage(str);
                if(ref.tfAddress.getText() != "" )
                ref.WriteFile(ref.tfAddress.getText(),"history");
                ref.enabled(); 
            } 
        }
       else if(e.getActionCommand().equals("3") || e.getActionCommand().equals("Refresh"))
        {
            if("".equals(ref.tfAddress.getText()))
                ref.tfAddress.setText(readline());
            ref.loadPage(ref.tfAddress.getText());
            ref.WriteFile(ref.tfAddress.getText(),"history");
            ref.s.push(ref.tfAddress.getText());
            ref.enabled();
            
        }
       else if(e.getActionCommand().equals("4") || e.getActionCommand().equals("View History"))
        {
            display(ref.read("history"));
        }
       else if(e.getActionCommand().equals("5"))
        {
            favorite();
        }
       else if(e.getActionCommand().equals("6") || e.getActionCommand().equals("Search"))
       {
           ref.strSearch = JOptionPane.showInputDialog("");
           String str = ref.jepMain.getText();
           JOptionPane.showMessageDialog(null, "Ocurrances Found : " + search(ref.strSearch,str));
       }
       
    }


    
    public int search(String strSearch,String str)
    {
        int count = 0, f =0;
        int i = 0;
        for( ; i < str.length(); i+=500)
        {
            if(i+500 < str.length())
            f = matchesIn(strSearch, str.substring(i,i+500));
            else
                break;
            count += f;
        }
        if(i<str.length())
        {
           i = str.length() - i;
           count += matchesIn(strSearch, str.substring(i,str.length()));
        }
        return count;
    }
    
    public static boolean isEmpty(String s) 
    {
        return (s == null || s.length() == 0);
    }

    public static int matchesIn(String text, String str)
    {
        int lastIndex = 0;
        int count = 0;
        String s = "";
        while(lastIndex != -1)
        {
            lastIndex = str.indexOf(text,lastIndex);
            if(lastIndex != -1){
                count ++;
                lastIndex += text.length();
            }
        }
        return count;
    }
    
    public String readline()
    {
       String str = "";
       try { 
            BufferedReader br = new BufferedReader(new FileReader(new File("history")));
            str = br.readLine();
            str = br.readLine();
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       return str;
    }
    
    public void display(String history)
    {
        JFrame  Frame = new JFrame();
        JEditorPane jepane = new JEditorPane();
         Frame.add(jepane);
         Frame.add(new JScrollPane(jepane));
         jepane.setText(history);
         jepane.setEditable(false);
         Frame.setSize(1100,300);
         Frame.setLocation(150, 10);
         Frame.setVisible(true);
         Frame.pack();
    }
    public void favorite()
    {
         JFrame  Frame = new JFrame();
         JButton b1 = new JButton("Add Favorites");
         GridBagLayout g = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();
         final String s = new String();
         Frame.setLayout(g);
         c.gridx = 0 ;
         c.gridy = 0;
         Frame.add(b1,c);
         c.gridy = 1;
         c.gridx = 0 ;
         c.gridwidth = 2;
         
         Frame.add( new JScrollPane(ref.textArea),c);
         ref.textArea.setText("Existing Favorites:\n\n" + ref.read("Favorites"));
         ref.textArea.setPreferredSize(new Dimension(600, 300));
         b1.setPreferredSize(new Dimension(600, 50));
         b1.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) 
             {
                 addf(ref.textArea);
             }
         });
         Frame.setLocation(400, 200);
         Frame.setVisible(true);
         Frame.pack();
         ref.textArea.setEditable(false);
    }
    public void addf(JTextArea ta)
    {
        String title = JOptionPane.showInputDialog("Enter Web Page Title");
        String url = JOptionPane.showInputDialog("Enter URl");
        ref.WriteFile(title+ " " + url,"Favorites");
        ta.setText(ref.read("Favorites"));
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) 
    {
        if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
        {
            ref.loadPage(e.getURL().toString());
            ref.tfAddress.setText(e.getURL().toString());
            ref.WriteFile(ref.tfAddress.getText(),"history");
            ref.s.push(ref.tfAddress.getText());
            ref.enabled();
        }
    }

    
}

