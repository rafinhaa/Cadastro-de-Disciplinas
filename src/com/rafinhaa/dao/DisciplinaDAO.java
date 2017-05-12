package com.rafinhaa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.rafinhaa.model.Disciplina;
import com.rafinhaa.model.Periodo;

public class DisciplinaDAO {

	public void inserir(Disciplina disciplina) {
		try (Connection conn = BD.getConnection()) {
			String sql = "insert into disciplina (cursoQueElaPertence,nome,cargaHoraria,numeroDeVagas,periodo) values (?,?,?,?,?)";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, disciplina.getCursoQueElaPertence());
				stmt.setString(2, disciplina.getNomeDaDiciplina());
				stmt.setFloat(3, disciplina.getCargaHoraria());
				stmt.setInt(4, disciplina.getNumeroDeVagas());
				stmt.setString(5, disciplina.getPeriodo());
				int linhaInseridas = stmt.executeUpdate();
				if (linhaInseridas > 0)
					JOptionPane.showMessageDialog(null, "A disciplina foi inserida com sucesso!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro:\n" + e.toString(), "Mensagem", JOptionPane.ERROR_MESSAGE);
		}
	}

	public List<Disciplina> listar() {
		List<Disciplina> disciplinas = new ArrayList<>();

		try (Connection conn = BD.getConnection()) {
			String sql = "select * from disciplina order by id asc";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						disciplinas.add(new Disciplina(rs.getInt("id"), rs.getString("nome"),
								rs.getFloat("cargaHoraria"), rs.getString("cursoQueElaPertence"),
								rs.getInt("NumeroDevagas"), Periodo.converte(rs.getString("periodo"))));
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro:\n" + e.toString(), "Mensagem", JOptionPane.ERROR_MESSAGE);
		}
		return disciplinas;
	}

	public void deletar(Disciplina disciplina) {
		try (Connection conn = BD.getConnection()) {
			String sql = "delete from disciplina where id = ?";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, disciplina.getId());
				int linhasDeletadas = stmt.executeUpdate();
				if (linhasDeletadas > 0)
					JOptionPane.showMessageDialog(null, "A disciplina foi deletada com sucesso!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro:\n" + e.toString(), "Mensagem", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void atualizar(Disciplina disciplina) {
		try (Connection conn = BD.getConnection()) {
			String sql = "update disciplina set nome=?, cursoQueElapertence=?, numeroDeVagas=?, periodo=? where id = ?";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, disciplina.getNomeDaDiciplina());
				stmt.setString(2, disciplina.getCursoQueElaPertence());
				stmt.setInt(3, disciplina.getNumeroDeVagas());
				stmt.setString(4, disciplina.getPeriodo());
				stmt.setInt(5, disciplina.getId());
				int linhasAtualizadas = stmt.executeUpdate();
				if (linhasAtualizadas > 0)
					JOptionPane.showMessageDialog(null, "A disciplina foi atualizada com sucesso!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro:\n" + e.toString(), "Mensagem", JOptionPane.ERROR_MESSAGE);
		}
	}
}
