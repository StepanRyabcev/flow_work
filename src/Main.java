import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import com.github.msteinbeck.sig4j.Dispatcher;
import com.github.msteinbeck.sig4j.signal.Signal1;

import calculate.DataChanged;
import javax.swing.JList;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private DataChanged dataChanged;
	private DefaultListModel<String> listModel;
	private JFrame frame;
	private reader rd;
	boolean needToDecr = false;
	private JTextField DecrKey;
	private JTextPane textPane;
	private static Main window;
	private JList<String> list;
	private boolean Continue = true;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}
	
	private void initialize() {
		dataChanged = new DataChanged();
		dataChanged.signal.connect(this::ShowList);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton open = new JButton("Open file"); 
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enpryptionOptions op;	
				op = new enpryptionOptions(needToDecr, DecrKey.getText());
				rd = new reader(op, dataChanged);
				if(Continue)
				{
				String out = rd.read();
				while(out.indexOf("(") >= 0)
					out = brackets.findAndParseBr(out);
				String result = director.parse(out);
				textPane = new JTextPane();
				frame.getContentPane().add(textPane, BorderLayout.EAST);
				textPane.setEditable(false);
				textPane.setText("Result: " + result);
				}
			}
		});
		frame.getContentPane().add(open, BorderLayout.NORTH);
		
		JCheckBox needDecrypt = new JCheckBox("Input file encrypted");
		frame.getContentPane().add(needDecrypt, BorderLayout.WEST);
		
		DecrKey = new JTextField();
		DecrKey.setToolTipText("Decryption key");
		frame.getContentPane().add(DecrKey, BorderLayout.SOUTH);
		DecrKey.setColumns(10);	
		

		DecrKey.setEditable(false);
		
		listModel = new DefaultListModel<>();
		list = new JList<String>(listModel);
		frame.getContentPane().add(list, BorderLayout.CENTER);
		needDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				needToDecr = !needToDecr;
				DecrKey.setEditable(needToDecr);
			}
		});
	}	

	public void ShowList(final Vector<String> files) {
		Continue = false;
		JButton select = new JButton("Select file"); 
		frame.getContentPane().add(select, BorderLayout.EAST);
		listModel.addAll(files);
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(list.getAnchorSelectionIndex() >= 0)
					{
					rd.SetFname(files.elementAt(list.getAnchorSelectionIndex()));
					String out = rd.read();
					while(out.indexOf("(") >= 0)
						out = brackets.findAndParseBr(out);
					String result = director.parse(out);
					textPane = new JTextPane();
					frame.getContentPane().add(textPane, BorderLayout.EAST);
					textPane.setEditable(false);
					textPane.setText("Result: " + result);
					select.hide();
					listModel.clear();
					}
			}
		});
	}
}