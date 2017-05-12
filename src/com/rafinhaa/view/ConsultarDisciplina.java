package com.rafinhaa.view;

import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableModel;

import com.rafinhaa.builders.DisciplinaBuilder;
import com.rafinhaa.controller.DisciplinaController;
import com.rafinhaa.model.Disciplina;
import com.rafinhaa.model.Periodo;
import com.rafinhaa.util.DisciplinaTableModel;

import net.miginfocom.swing.MigLayout;

public class ConsultarDisciplina {

	private JFrame frmConsultaDeDisciplinas;
	private JTextField txtNome;
	private JTable table;
	private JTextField txtCurso;
	private JTextField txtCargaHoraria;
	private JTextField txtVagas;
	private JComboBox<Periodo> cmbPeriodo;
	private DisciplinaController dController;
	private DisciplinaTableModel tableModel;
	private JLabel lblId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarDisciplina window = new ConsultarDisciplina();
					window.frmConsultaDeDisciplinas.setVisible(true);
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
	public ConsultarDisciplina() {
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
		frmConsultaDeDisciplinas = new JFrame();
		frmConsultaDeDisciplinas.setTitle("Consulta De Disciplinas");
		frmConsultaDeDisciplinas.setBounds(100, 100, 456, 420);
		frmConsultaDeDisciplinas.getContentPane()
				.setLayout(new MigLayout("", "[][grow][][][]", "[][][][][][][][grow]"));
		frmConsultaDeDisciplinas.setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("Disciplina:");
		frmConsultaDeDisciplinas.getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing");

		txtNome = new JTextField();
		frmConsultaDeDisciplinas.getContentPane().add(txtNome, "cell 1 0,growx");
		txtNome.setColumns(10);

		lblId = new JLabel("");
		lblId.setVisible(false);
		frmConsultaDeDisciplinas.getContentPane().add(lblId, "cell 2 0");

		JLabel lblNewLabel_1 = new JLabel("Curso:");
		frmConsultaDeDisciplinas.getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");

		txtCurso = new JTextField();
		frmConsultaDeDisciplinas.getContentPane().add(txtCurso, "cell 1 1,growx");
		txtCurso.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Periodo:");
		frmConsultaDeDisciplinas.getContentPane().add(lblNewLabel_2, "cell 0 2,alignx trailing");

		cmbPeriodo = new JComboBox<>();
		cmbPeriodo.setModel(new DefaultComboBoxModel<>(Periodo.values()));
		frmConsultaDeDisciplinas.getContentPane().add(cmbPeriodo, "cell 1 2,growx");

		JLabel lblNewLabel_3 = new JLabel("Carga Horaria:");
		frmConsultaDeDisciplinas.getContentPane().add(lblNewLabel_3, "cell 0 3,alignx trailing");

		txtCargaHoraria = new JTextField();
		frmConsultaDeDisciplinas.getContentPane().add(txtCargaHoraria, "cell 1 3,growx");
		txtCargaHoraria.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Vagas:");
		frmConsultaDeDisciplinas.getContentPane().add(lblNewLabel_4, "cell 0 4,alignx trailing");

		txtVagas = new JTextField();
		frmConsultaDeDisciplinas.getContentPane().add(txtVagas, "cell 1 4,growx");
		txtVagas.setColumns(10);

		JButton btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				evento("deletar");
			}
		});
		frmConsultaDeDisciplinas.getContentPane().add(btnNewButton_1, "cell 3 5");

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				evento("alterar");
			}
		});
		frmConsultaDeDisciplinas.getContentPane().add(btnAlterar, "cell 4 5");

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Disciplina disciplina = tableModel.getValue(table.getSelectedRow());
				disciplinaBuilderReverse(disciplina);
			}
		});
		preencherTabela();
		JScrollPane scrollPane = new JScrollPane(table);
		frmConsultaDeDisciplinas.getContentPane().add(scrollPane);
		frmConsultaDeDisciplinas.getContentPane().add(scrollPane, "cell 0 6 5 2,grow");
	}

	private void limparCampos() {
		txtNome.setText("");
		txtCurso.setText("");
		cmbPeriodo.setSelectedItem(0);
		txtCargaHoraria.setText("");
		txtVagas.setText("");
		lblId.setText("");
	}

	private void preencherTabela() {
		dController = new DisciplinaController();
		List<Disciplina> disciplinas = dController.listarDisciplinas();
		tableModel = new DisciplinaTableModel(disciplinas);
		table.setModel(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void alterar() {
		dController.atualizar(disciplinaBuilder());
		limparCampos();
		preencherTabela();
	}

	private Disciplina disciplinaBuilder() {
		return new DisciplinaBuilder()
				.comId(Integer.parseInt(lblId.getText()))
				.comNome(txtNome.getText()).comCurso(txtCurso.getText())
				.comPeriodo((Periodo) cmbPeriodo.getSelectedItem())
				.comCargaHoraria(Float.parseFloat(txtCargaHoraria.getText()))
				.comVagas(Integer.parseInt(txtVagas.getText()))
				.builder();
	}

	private void deletar() {
		dController.deletar(disciplinaBuilder());
		limparCampos();
		preencherTabela();
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
			int optionDialog = JOptionPane.showOptionDialog(null, "Deseja "+ evento +" essa disciplina?", "Informação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (optionDialog == 0) {
				switch (evento) {
				case "alterar":
					alterar();
					break;
				case "deletar":
					deletar();
					break;
				default:
					break;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro:\n Preencha todos os campos!", "Mensagem",
					JOptionPane.ERROR_MESSAGE);
		}
	}
/*
	private Disciplina disciplinaBuilder() {
		dController = new DisciplinaController();
		String nomeDaDiciplina = txtNome.getText();
		String cursoQueElaPertence = txtCurso.getText();
		Periodo periodo = (Periodo) cmbPeriodo.getSelectedItem();
		Float cargaHoraria = Float.parseFloat(txtCargaHoraria.getText());
		int numeroDeVagas = Integer.parseInt(txtVagas.getText());
		if (lblId.getText().length() > 0) {
			int iD = Integer.parseInt(lblId.getText());
			return new Disciplina(iD, nomeDaDiciplina, cargaHoraria, cursoQueElaPertence, numeroDeVagas, periodo);
		} else
			return new Disciplina(nomeDaDiciplina, cargaHoraria, cursoQueElaPertence, numeroDeVagas, periodo);
	}
	*/
	private void disciplinaBuilderReverse(Disciplina disciplina){
		lblId.setText(disciplina.getId()+"");
		txtNome.setText(disciplina.getNomeDaDiciplina());
		txtCurso.setText(disciplina.getCursoQueElaPertence());
		cmbPeriodo.setSelectedIndex(Periodo.obtemId(disciplina.getPeriodo()));
		txtCargaHoraria.setText(disciplina.getCargaHoraria()+"");
		txtVagas.setText(disciplina.getNumeroDeVagas()+"");
	}

}
