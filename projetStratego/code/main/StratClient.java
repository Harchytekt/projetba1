package main;

import gui.InitWindow;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class StratClient extends JFrame{
	private Client client=new Client();
	private String adIp = "127.0.0.1";
	private int state, Oplayer;
	private JLabel lab=new JLabel();
	
	
	public StratClient(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		askIp();
		connect();
		System.out.println("connect "+client.isConnected());
		this.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
			     close();
			   }
			  });
		
		this.setSize(400, 400);
		this.setTitle("Client");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		state();
		Kryo kryo = client.getKryo();
	    kryo.register(Game.class);
	    kryo.register(GridIA.class);
	    kryo.register(pawn.APawn.class);
	    kryo.register(pawn.APawn[].class);
	    kryo.register(pawn.APawn[][].class);
	    kryo.register(pawn.Bomb.class);
	    kryo.register(pawn.Captain.class);
	    kryo.register(pawn.Colonel.class);
	    kryo.register(pawn.Flag.class);
	    kryo.register(pawn.General.class);
	    kryo.register(pawn.Lake.class);
	    kryo.register(pawn.Lieutenant.class);
	    kryo.register(pawn.Major.class);
	    kryo.register(pawn.Marshal.class);
	    kryo.register(pawn.Miner.class);
	    kryo.register(pawn.NoPawn.class);
	    kryo.register(pawn.Scout.class);
	    kryo.register(pawn.Sergeant.class);
	    kryo.register(pawn.Spy.class);
	    kryo.register(java.util.Vector.class);
	    kryo.register(int[].class);
	    
	    
	    
	    
	}
	
	private void state() {
		if(state==1){
//			JPanel panel = new JPanel();
//			lab = new JLabel("Vous �tes le joueur 1, veuillez attendre votre adversaire");
			lab.setText("Vous �tes le joueur 1, veuillez attendre votre adversaire");
			lab.setFont(new Font(" TimesRoman ",Font.BOLD,13));
			this.add(lab);
			this.repaint();
//			this.setContentPane(panel);
		}
		if(state==2){
//			JPanel panel = new JPanel();
//			lab = new JLabel("Partie en cours, veuillez ne pas fermer cette fen�tre");
			lab.setText("Partie en cours, veuillez ne pas fermer cette fen�tre");
			lab.setFont(new Font(" TimesRoman ",Font.BOLD,13));
			this.add(lab);
			this.repaint();
//			this.setContentPane(panel);
		}
		
	}

	private void close() {
		client.close();
		System.out.println("close");
		this.dispose();
	}

	private void connect() {
		// cr�e l'objet
		client.addListener(new Listener(){
			public void received(Connection connection,Object object){
				if(object instanceof String){
					System.out.println("String");
					System.out.println(object);
					if(((String)object).equals("FULL")){
						JOptionPane jop = new JOptionPane();
						jop.showMessageDialog(null, "Connexion impossible, le serveur est plein, veuillez patienter", "Serveur plein", JOptionPane.INFORMATION_MESSAGE);
						close();
					}
					if(((String)object).equals("GO")){
						System.out.println("GOGOGOGOGO");
						state=2;
						state();
						new InitWindow(client,Oplayer);
					}
					
				}
				if(object instanceof Integer){
					System.out.println("integer");
					System.out.println(object);
					if((Integer)object == 1){
						state = 1;
//						System.out.println("state=1");
						state();
					}
					if((Integer)object == 2){
						state = 2;
						state();
					}
					if((Integer)object == -1){
						Oplayer = 1;
					}
					if((Integer)object == -2){
						Oplayer = 2;
					}
				}
			}
		});
		client.start();
		
	    try {
			client.connect(5000,adIp,54555,54777);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "Connexion impossible, veuillez verifier l'adresse.", "Erreur", JOptionPane.ERROR_MESSAGE);
			askIp();
			connect();
		}
		
	}

	public void askIp(){
		JOptionPane jop = new JOptionPane();
		String adIp2 = jop.showInputDialog(null, "Veuillez taper l'adresse ip du serveur", "Adresse IP", JOptionPane.QUESTION_MESSAGE);
		if(!adIp2.isEmpty()){
			adIp=adIp2;
		}
	}
}
