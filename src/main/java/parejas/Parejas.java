package parejas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Parejas {
	static ImageIcon imagen[][] = new ImageIcon[4][5];
	static ImageIcon ima = new ImageIcon("imagenes/trebol.jpg");
	static JFrame jVentana = new JFrame();
	static JFrame ventp = new JFrame();
	static JPanel jContenedor = new JPanel();
	static JLabel jtexto = new JLabel();
	static JButton jbotones[][] = new JButton[4][5];
	static JButton jnuevo = new JButton("Nuevo");
	static int matriz[][] = new int[4][5];
	static int vector[] = new int[10];
	static int posiciones[][] = new int[2][2];
	static int nc = 0;
	static int naciert = 0;
	static int nint = 0;
	static String nombre = "";
	static int a = 0;

	public static void main(String[] args) {
		ventanaPrincipal();
		CrearBotones();
		numeroAleatorio();
		imagenes();
	}

	static void ventanaPrincipal() {
		ventp.setBounds(500, 200, 600, 450);
		ventp.setTitle("                                          :::::::::::::::::: JUEGO DE PAREJAS ::::::::::::::::::");
		ventp.setLayout(null);
		ventp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventp.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"imagenes/logo.png"));
		ventp.setResizable(false);

		final JTextField nom = new JTextField();
		final JButton btn = new JButton();

		nom.setBounds(224, 232, 158, 20);
		ventp.add(nom);

		btn.setEnabled(false);

		do {
			a = 0;
			nom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					nombre = evento.getActionCommand();
					if (nombre.equals("")
							|| nombre.equals(String.valueOf((char) 32)))
						a = 1;
					else {
						btn.setEnabled(true);
						nom.setEnabled(false);
					}
				}
			});
		} while (a == 1);

		btn.setBounds(241, 285, 125, 39);
		btn.setIcon(new ImageIcon("imagenes/boton.jpg"));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana();
			}
		});
		ventp.add(btn);

		JLabel ima = new JLabel();
		ima.setBounds(0, 0, 600, 450);
		ima.setIcon(new ImageIcon("imagenes/fondoprincipal.jpg"));
		ventp.add(ima);

		ventp.setVisible(true);
	}

	static void mensajeFinal() {
		if (naciert == 10)
			JOptionPane.showMessageDialog(null, "BIEN HECHO  " + nombre
					+ "  HAS GANADO EN:\n " + nint + " INTENTOS");
	}

	static void Ventana() {
		ventp.setVisible(false);
		jVentana.setTitle("                                          :::::::::::::::::: JUEGO DE PAREJAS ::::::::::::::::::");
		jVentana.setBounds(500, 200, 650, 460);
		jVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jVentana.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"imagenes/logo.png"));
		jVentana.setLayout(null);
		jVentana.setResizable(false);

		jContenedor.setBounds(10, 50, 400, 370);
		jContenedor.setLayout(new GridLayout(4, 5));
		jVentana.add(jContenedor);

		jnuevo.setBounds(450, 90, 80, 30);
		jnuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		jVentana.add(jnuevo);

		JButton acerde = new JButton("ACERCA DE:");
		acerde.setBounds(450, 50, 120, 30);
		acerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acercade();
			}
		});
		jVentana.add(acerde);

		JLabel ima = new JLabel();
		ima.setBounds(0, 0, 650, 430);
		ima.setIcon(new ImageIcon("imagenes/fondojuego.jpg"));
		jVentana.add(ima);

		jVentana.setVisible(true);
	}

	static void CrearBotones() {
		for (int fila = 0; fila < jbotones.length; fila++)
			for (int columna = 0; columna < jbotones[fila].length; columna++) {
				jbotones[fila][columna] = new JButton();
				jbotones[fila][columna].addActionListener(new Escuchador());
				jContenedor.add(jbotones[fila][columna]);
				jbotones[fila][columna].setBackground(Color.WHITE);
				jbotones[fila][columna].setIcon(ima);
			}
	}

	static void numeroAleatorio() {
		int n;
		int bn = 0;
		for (int i = 0; i < vector.length; i++) {
			n = (int) (Math.random() * 20 + 1);
			for (int j = 0; j < vector.length; j++) {
				if (n == vector[j] || n == 0) {
					i--;
					bn = 0;
					break;
				}
				bn = 1;
			}
			if (bn == 1)
				vector[i] = n;
		}
		for (int x = 0; x < vector.length; x++) {
			int rp = 0;
			do {
				int f = (int) (Math.random() * 4);
				int c = (int) (Math.random() * 5);
				if (matriz[f][c] == 0) {
					matriz[f][c] = vector[x];
					rp++;
				}
			} while (rp < 2);
		}
	}

	static void imagenes() {
		for (int i = 0; i < matriz.length; i++)
			for (int j = 0; j < matriz[i].length; j++)
				imagen[i][j] = new ImageIcon("imagenes/"
						+ String.valueOf(matriz[i][j]) + ".JPG");
	}

	static void ImagenesBoton() {
		for (int i = 0; i < matriz.length; i++)
			for (int j = 0; j < matriz[i].length; j++)
				jbotones[i][j].setIcon(matriz[i][j] == 0 ? imagen[i][j] : ima);
	}

	static class Escuchador implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			for (int i = 0; i < jbotones.length; i++) {
				for (int j = 0; j < jbotones[i].length; j++) {
					if (evento.getSource() == jbotones[i][j]
							&& matriz[i][j] != 0) {
						if (nc == 0) {
							posiciones[nc][0] = i;
							posiciones[nc][1] = j;
							jbotones[i][j].setIcon(imagen[i][j]);
							nc++;
						} else if (nc == 1) {
							if (i != posiciones[0][0] || j != posiciones[0][1]) {
								posiciones[nc][0] = i;
								posiciones[nc][1] = j;
								jbotones[i][j].setIcon(imagen[i][j]);
								if (matriz[posiciones[0][0]][posiciones[0][1]] == matriz[posiciones[1][0]][posiciones[1][1]]) {
									matriz[posiciones[0][0]][posiciones[0][1]] = 0;
									matriz[posiciones[1][0]][posiciones[1][1]] = 0;
									naciert++;
									nint++;
								} else
									nint++;
								nc++;
							}
						} else {
							ImagenesBoton();
							jbotones[i][j].setIcon(imagen[i][j]);
							nc = 0;
							posiciones[nc][0] = i;
							posiciones[nc][1] = j;
							nc = 1;
						}
					}
				}
				jVentana.setTitle("    :::::::::::::::::: JUEGO DE PAREJAS ::::::::::::::::::    "
						+ nint + " intentos --- " + naciert + " aciertos");
			}
			mensajeFinal();
		}
	}

	static void nuevo() {
		matriz = new int[4][5];
		vector = new int[10];
		nc = 0;
		naciert = 0;
		nint = 0;
		numeroAleatorio();
		imagenes();
		for (int y = 0; y < jbotones.length; y++)
			for (int x = 0; x < jbotones[y].length; x++)
				jbotones[y][x].setIcon(ima);
	}

	static void acercade() {
		JFrame ventayu = new JFrame();
		ventayu.setBounds(500, 200, 480, 370);
		ventayu.setTitle("ACERCA DE");
		ventayu.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"imagenes/logo.png"));
		ventayu.setLayout(null);

		JLabel lblUniversidadTecnicaDe = new JLabel(
				"UNIVERSIDAD TECNICA DE MACHALA");
		lblUniversidadTecnicaDe.setFont(new Font("Elephant", Font.PLAIN, 16));
		lblUniversidadTecnicaDe.setForeground(new Color(0, 0, 139));
		lblUniversidadTecnicaDe.setBackground(Color.WHITE);
		lblUniversidadTecnicaDe.setBounds(36, 11, 391, 53);
		ventayu.add(lblUniversidadTecnicaDe);

		JLabel lblFacultadIngenieriaCivil = new JLabel(
				"FACULTAD INGENIERIA CIVIL");
		lblFacultadIngenieriaCivil.setForeground(new Color(233, 150, 122));
		lblFacultadIngenieriaCivil.setFont(new Font("Rockwell", Font.BOLD
				| Font.ITALIC, 13));
		lblFacultadIngenieriaCivil.setBounds(132, 59, 199, 24);
		ventayu.add(lblFacultadIngenieriaCivil);

		JLabel lblEscuelaDeInformatica = new JLabel("ESCUELA DE INFORMATICA");
		lblEscuelaDeInformatica.setForeground(new Color(75, 0, 130));
		lblEscuelaDeInformatica.setFont(new Font("Lucida Calligraphy",
				Font.BOLD | Font.ITALIC, 14));
		lblEscuelaDeInformatica.setBounds(103, 94, 257, 24);
		ventayu.add(lblEscuelaDeInformatica);

		JLabel lblAutor = new JLabel("AUTOR:");
		lblAutor.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		lblAutor.setBounds(49, 150, 76, 23);
		ventayu.add(lblAutor);

		JLabel lblTutor = new JLabel("TUTOR:");
		lblTutor.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		lblTutor.setBounds(49, 184, 46, 14);
		ventayu.add(lblTutor);

		JLabel lblQuimestre = new JLabel("QUIMESTRE:");
		lblQuimestre.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		lblQuimestre.setBounds(49, 223, 76, 24);
		ventayu.add(lblQuimestre);

		JLabel lblNewLabel = new JLabel("ASIGNATURA:");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		lblNewLabel.setBounds(49, 264, 98, 24);
		ventayu.add(lblNewLabel);

		JLabel lblAo = new JLabel("AÑO:");
		lblAo.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		lblAo.setBounds(49, 319, 98, 14);
		ventayu.add(lblAo);

		JLabel lblYuleisyMarilinOchoa = new JLabel(
				"YULEISY MARILIN OCHOA GONZALEZ");
		lblYuleisyMarilinOchoa.setFont(new Font("Microsoft PhagsPa", Font.BOLD
				| Font.ITALIC, 13));
		lblYuleisyMarilinOchoa.setBounds(202, 148, 297, 24);
		ventayu.add(lblYuleisyMarilinOchoa);

		JLabel lblIngJonathanAguilar = new JLabel("ING. JONATHAN AGUILAR");
		lblIngJonathanAguilar.setFont(new Font("Microsoft PhagsPa", Font.BOLD
				| Font.ITALIC, 13));
		lblIngJonathanAguilar.setBounds(202, 183, 199, 14);
		ventayu.add(lblIngJonathanAguilar);

		JLabel lblPrimero = new JLabel("PRIMERO");
		lblPrimero.setFont(new Font("Microsoft PhagsPa", Font.BOLD
				| Font.ITALIC, 13));
		lblPrimero.setBounds(202, 227, 167, 14);
		ventayu.add(lblPrimero);

		JLabel lblProgramacionEstructura = new JLabel("PROGRAMACION ESTRUCTURA");
		lblProgramacionEstructura.setFont(new Font("Microsoft PhagsPa",
				Font.BOLD | Font.ITALIC, 13));
		lblProgramacionEstructura.setBounds(202, 266, 222, 19);
		ventayu.add(lblProgramacionEstructura);

		JLabel label_1 = new JLabel("2012 - 2013");
		label_1.setFont(new Font("Microsoft PhagsPa", Font.BOLD | Font.ITALIC,
				13));
		label_1.setBounds(201, 313, 105, 24);
		ventayu.add(label_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("imagenes/UNIVERSIDAD.jpg"));
		lblNewLabel_1.setBounds(7, 50, 88, 89);
		ventayu.add(lblNewLabel_1);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("imagenes/ESCUELA.jpg"));
		label_2.setBounds(365, 50, 95, 89);
		ventayu.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("imagenes/FONDO.jpg"));
		label_3.setBounds(0, 0, 473, 342);
		ventayu.add(label_3);

		ventayu.setResizable(false);
		ventayu.setVisible(true);
	}

}