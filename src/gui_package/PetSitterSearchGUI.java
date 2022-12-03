package gui_package;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import database_package.PetSitterDBConnector;
import reservation_package.PetSitter;

public class PetSitterSearchGUI extends ReservationMainGUI{
	private PetSitterDBConnector conn;

	public PetSitterSearchGUI(String title) {
		createFrame(title);
		this.conn = new PetSitterDBConnector();
		// 나중에 LocalDate, LocalTime now에서 변경 필요
		//임시 resvDate, resvTime
		LocalDate resvDate = LocalDate.now();
		LocalTime resvTime = LocalTime.parse("10:00");
		this.add(infoPanel(resvDate, resvTime), BorderLayout.NORTH);
		LinkedList<PetSitter> petsitterDB = new LinkedList<PetSitter>();
		
		try {
			petsitterDB = this.conn.searchDBwithTime(resvDate, resvTime);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JComboBox addrCombo = addressComboBox();
		
		JPanel tablePanel = new JPanel();
		try {
			tablePanel = showList(petsitterDB);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		JLabel ex = new JLabel();
		tablePanel.add(addrCombo, BorderLayout.NORTH);
//		tablePanel.add(ex, BorderLayout.SOUTH);
		

//		addrCombo.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String addr = addrCombo.getSelectedItem().toString();
//				// 임시 구현
//				try {
//					if(addr == "선택") {
//						petsitterDB = conn.searchDBwithTime(resvDate, resvTime);
//					}
//					else {
//						petsitterDB = conn.searchDBwithAddress(petsitterDB, addr);
//					}
//					tablePanel.add(showList(petsitterDB));
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				ex.setText("선택된 주소:  " + addr);
//			}
//			
//		});

		
		this.add(tablePanel, BorderLayout.CENTER);
		JButton resvButton = new JButton("선택하기");
		this.add(resvButton, BorderLayout.PAGE_END);
		this.setVisible(true);
		this.setFocusable(true);
	}
	
	public JPanel infoPanel(LocalDate resvDate, LocalTime resvTime) {
		JPanel info = new JPanel();
		JLabel resvDateInfo = new JLabel(resvDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
		JLabel resvTimeInfo = new JLabel(resvTime.format(DateTimeFormatter.ofPattern("HH:mm에 예약을 진행합니다.")));
		info.add(resvDateInfo);
		info.add(resvTimeInfo);
		return info;
	}
	
	public JPanel showList(LinkedList<PetSitter> petsitterDB) throws IOException {
		JPanel cList = new JPanel();
		JLabel select = new JLabel();
		cList.setLayout(new BorderLayout());
		
		JPanel subList = new JPanel();		
		
		String[] header = this.conn.getDBHeader();
		String[][] contents = new String[petsitterDB.size()][19];
		for (int i = 0; i < petsitterDB.size();i++) {
			try {
				contents[i] = petsitterDB.get(i).getAttributeInList();
//				System.out.println(String.join(",", contents[i]));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JTable petsitterListTable = new JTable(contents, header);
		petsitterListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(petsitterListTable);
		petsitterListTable.getColumnModel().getColumn(0).setMinWidth(200);
		petsitterListTable.getColumnModel().getColumn(1).setMinWidth(150);
		petsitterListTable.getColumnModel().getColumn(2).setMinWidth(420);
		
		MouseListener tableListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable table = (JTable) e.getSource();
				int row = table.getSelectedRow();
//				System.out.print(table.getModel().getValueAt(row,0 )+"\t");
				select.setText(table.getModel().getValueAt(row,0)+"에 예약을 진행합니다.");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		petsitterListTable.addMouseListener(tableListener);
		
		
		
		JScrollPane scrollTable = new JScrollPane(petsitterListTable);
		scrollTable.setPreferredSize(new Dimension(780, 200));
		cList.add(new JLabel("선택한 예약일에 대한 도우미 조회 결과입니다."), BorderLayout.NORTH);

		subList.add(scrollTable, BorderLayout.CENTER);
		subList.add(select, BorderLayout.SOUTH);
		cList.add(subList, BorderLayout.CENTER);
		return cList;
	
	}
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 50; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	public JComboBox addressComboBox() {
		String address[] = {"선택", "대구광역시 북구", "대구광역시 중구", "대구광역시 동구"};
		JComboBox addrCombo = new JComboBox(address); 
		return addrCombo;
	}

}