package com.rafinhaa.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.rafinhaa.builders.DisciplinaBuilder;
import com.rafinhaa.controller.DisciplinaController;
import com.rafinhaa.model.Disciplina;
import com.rafinhaa.model.Periodo;

import net.miginfocom.swing.MigLayout;

public class Main {

	private JFrame frmCadastroDeDisciplinas;
	private JTextField txtNome;
	private JTextField txtCurso;
	private JTextField txtCargaHoraria;
	private JTextField txtVagas;
	private JComboBox<Periodo> cmbPeriodo;
	private DisciplinaController dController;
	private ConsultarDisciplina consultarDisciplina = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmCadastroDeDisciplinas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	private void initialize() {
		frmCadastroDeDisciplinas = new JFrame();
		frmCadastroDeDisciplinas.setTitle("Cadastro de Disciplinas");
		frmCadastroDeDisciplinas.setBounds(100, 100, 333, 198);
		frmCadastroDeDisciplinas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeDisciplinas.getContentPane().setLayout(new MigLayout("", "[][grow,center]", "[][][][][][]"));
		frmCadastroDeDisciplinas.setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("Disciplina:");
		frmCadastroDeDisciplinas.getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing");

		txtNome = new JTextField();
		frmCadastroDeDisciplinas.getContentPane().add(txtNome, "cell 1 0,growx");
		txtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Curso:");
		frmCadastroDeDisciplinas.getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");

		txtCurso = new JTextField();
		frmCadastroDeDisciplinas.getContentPane().add(txtCurso, "cell 1 1,growx");
		txtCurso.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Periodo:");
		frmCadastroDeDisciplinas.getContentPane().add(lblNewLabel_2, "cell 0 2,alignx trailing");

		cmbPeriodo = new JComboBox<>();
		cmbPeriodo.setModel(new DefaultComboBoxModel<>(Periodo.values()));
		frmCadastroDeDisciplinas.getContentPane().add(cmbPeriodo, "cell 1 2,growx");

		JLabel lblNewLabel_3 = new JLabel("Carga Horaria:");
		frmCadastroDeDisciplinas.getContentPane().add(lblNewLabel_3, "cell 0 3,alignx trailing");

		txtCargaHoraria = new JTextField();
		frmCadastroDeDisciplinas.getContentPane().add(txtCargaHoraria, "cell 1 3,growx");
		txtCargaHoraria.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Vagas:");
		frmCadastroDeDisciplinas.getContentPane().add(lblNewLabel_4, "cell 0 4,alignx trailing");

		txtVagas = new JTextField();
		frmCadastroDeDisciplinas.getContentPane().add(txtVagas, "cell 1 4,growx");
		txtVagas.setColumns(10);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				evento("cadastrar");
			}
		});
		frmCadastroDeDisciplinas.getContentPane().add(btnNewButton, "flowx,cell 1 5");

		JButton btnAlterar = new JButton("Consultar");
		btnAlterar.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				consultarDisciplina = new ConsultarDisciplina();
				consultarDisciplina.main(null);
			}
		});
		frmCadastroDeDisciplinas.getContentPane().add(btnAlterar, "cell 1 5");
	}

	private void cadastrar() {
		dController = new DisciplinaController();
		dController.inserirDisciplina(new DisciplinaBuilder()
				.comNome(txtNome.getText()).comCurso(txtCurso.getText())
				.comPeriodo((Periodo) cmbPeriodo.getSelectedItem())
				.comCargaHoraria(Float.parseFloat(txtCargaHoraria.getText()))
				.comVagas(Integer.parseInt(txtVagas.getText())).builder());
		limparCampos();
	}

	private void limparCampos() {
		txtNome.setText("");
		txtCurso.setText("");
		cmbPeriodo.setSelectedItem(0);
		txtCargaHoraria.setText("");
		txtVagas.setText("");
	}

	private boolean validarCampos() {
		if (txtNome.getText().length() > 0 && txtCurso.getText().length() > 0 && txtCargaHoraria.getText().length() > 0
				&& txtVagas.getText().length() > 0)
			return true;
		else
			return false;
	}

	private void evento(String evento) {
		if (validarCampos()) {
			Object[] options = { "Sim", "Não" };
			int optionDialog = JOptionPane.showOptionDialog(null, "Deseja inserir essa disciplina?", "Informação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (optionDialog == 0) {
				cadastrar();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro:\n Preencha todos os campos!", "Mensagem",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
