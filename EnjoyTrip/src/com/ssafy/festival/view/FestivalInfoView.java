package com.ssafy.festival.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.festival.model.dto.FestivalDto;
import com.festival.model.service.FestivalService;
import com.festival.model.service.FestivalServiceImpl;

public class FestivalInfoView {

    private FestivalService festivalService;

    private JFrame frame;
    private JComboBox<String> regionComboBox;
    private JButton searchButton;

    private DefaultTableModel festivalModel;
    private JTable festivalTable;

    private String[] regions = {
        "지역선택",
        "서울",
        "부산",
        "대구",
        "인천",
        "광주",
        "대전",
        "울산",
        "세종",
        "경기",
        "강원",
        "충북",
        "충남",
        "전북",
        "전남",
        "경북",
        "경남",
        "제주"
    };

    private String[] title = {
        "광역자치단체명",
        "축제명"
    };

    public FestivalInfoView() {
        festivalService = new FestivalServiceImpl();

        frame = new JFrame("지역 축제 조회");

        setMain();

        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setMain() {
        JPanel topPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        regionComboBox = new JComboBox<>(regions);
        searchButton = new JButton("조회");

        topPanel.add(new JLabel("지역"));
        topPanel.add(regionComboBox);
        topPanel.add(searchButton);

        festivalModel = new DefaultTableModel(title, 0);
        festivalTable = new JTable(festivalModel);

        JScrollPane scrollPane = new JScrollPane(festivalTable);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchFestivals());
    }

    private void searchFestivals() {
        String region =
            (String) regionComboBox.getSelectedItem();

        if (region == null || region.equals("지역선택")) {
            festivalModel.setRowCount(0);
            return;
        }

        List<FestivalDto> festivals =
            festivalService.searchByRegion(region);

        String[][] data =
            new String[festivals.size()][2];

        for (int i = 0; i < festivals.size(); i++) {
            FestivalDto festival = festivals.get(i);

            data[i][0] = festival.getRegion();
            data[i][1] = festival.getFestivalName();
        }

        festivalModel.setDataVector(data, title);
    }
}