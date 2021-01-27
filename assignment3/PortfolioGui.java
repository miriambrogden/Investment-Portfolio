package assignment3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PortfolioGui extends JFrame implements Serializable {

    ArrayList<Investment> list = new ArrayList<>();
    private String name = "";
    private String symbol = "";
    private int quantity = -10;
    private double price = -10;
    private int current = 0;
    private int first = 0;
    private int last = 0;
    private String searchSymbol = "";
    private String searchKeywords = "";
    private double searchLow = -10;
    private double searchHigh = -10;

    public PortfolioGui(String fileName) {
        // main layout
        super("Investment Portfolio");
        Portfolio portfolio = new Portfolio();
        if (portfolio.readMe(list, fileName) != null) {
            list = portfolio.readMe(list, fileName);
        }
        this.setSize(new Dimension(1000, 700));
        this.setLayout(new GridLayout(3, 1, 10, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(255, 255, 255));

        //commands
        JPanel commandsPanel = new JPanel(new GridLayout(3, 1));
        JMenu commandsMenu = new JMenu("Commands");
        commandsMenu.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        commandsMenu.setPreferredSize(new Dimension(203, 25));
        JMenuItem buy = new JMenuItem("Buy");
        buy.setPreferredSize(new Dimension(200, 50));
        buy.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        commandsMenu.add(buy);
        JMenuItem sell = new JMenuItem("Sell");
        sell.setPreferredSize(new Dimension(200, 50));
        sell.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        commandsMenu.add(sell);
        JMenuItem update = new JMenuItem("Update");
        update.setPreferredSize(new Dimension(200, 50));
        update.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        commandsMenu.add(update);
        JMenuItem getGain = new JMenuItem("Get Gain");
        getGain.setPreferredSize(new Dimension(200, 50));
        getGain.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        commandsMenu.add(getGain);
        JMenuItem search = new JMenuItem("Search");
        search.setPreferredSize(new Dimension(200, 50));
        search.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        commandsMenu.add(search);
        JMenuItem quit = new JMenuItem("Quit");
        quit.setPreferredSize(new Dimension(200, 50));
        quit.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        commandsMenu.add(quit);
        JMenuBar bar = new JMenuBar();
        commandsPanel.setBackground(new Color(255, 255, 255));
        bar.setBackground(new Color(102, 205, 170));
        bar.add(commandsMenu);
        commandsPanel.add(bar);
        JLabel fillerLab1 = new JLabel("");
        commandsPanel.add(fillerLab1);
        JLabel buyingLab = new JLabel("Buying an Investment");
        buyingLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        buyingLab.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        buyingLab.setVisible(false);
        JLabel sellingLab = new JLabel("Selling an Investment");
        sellingLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        sellingLab.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        sellingLab.setVisible(false);
        JLabel updatingLab = new JLabel("Updating Investments");
        updatingLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        updatingLab.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        updatingLab.setVisible(false);
        JLabel gainLab = new JLabel("Getting Total Gain");
        gainLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        gainLab.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        gainLab.setVisible(false);
        JLabel searchLab = new JLabel("Searching Investments");
        searchLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        searchLab.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        searchLab.setVisible(false);

        //whitespace
        JPanel filler = new JPanel(new GridLayout(1, 1));
        JLabel fillerText = new JLabel("");
        filler.setBackground(new Color(255, 255, 255));
        filler.add(fillerText);

        //welcome
        JPanel welcomePanel = new JPanel(new GridLayout(1, 1, 10, 10));
        JTextArea welcomeText = new JTextArea(10, 10);
        welcomeText.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        welcomeText.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        welcomeText.setText("Welcome to the Investment Portfolio. Please choose a command from the 'Commands' menu to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program.");
        welcomeText.setEditable(false);
        welcomeText.setLineWrap(true);
        welcomePanel.add(welcomeText);

        //buy panel
        JPanel buyPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        buyPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        buyPanel.setBackground(new Color(255, 255, 255));
        JLabel typeLab = new JLabel("Type");
        typeLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        String[] type = {"Stock", "Mutual Fund"};
        JComboBox typeList = new JComboBox(type);
        typeList.setSelectedIndex(0);
        buyPanel.add(typeLab);
        buyPanel.add(typeList);
        JLabel fillerLab2 = new JLabel("");
        buyPanel.add(fillerLab2);
        JLabel fillerLab3 = new JLabel("");
        buyPanel.add(fillerLab3);
        JLabel symbolLab = new JLabel("Symbol");
        symbolLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField symbolInput = new JTextField(20);
        buyPanel.add(symbolLab);
        buyPanel.add(symbolInput);
        JLabel fillerLab4 = new JLabel("");
        buyPanel.add(fillerLab4);
        JButton resetButt = new JButton("Reset");
        buyPanel.add(resetButt);
        JLabel nameLab = new JLabel("Name");
        nameLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField nameInput = new JTextField(20);
        buyPanel.add(nameLab);
        buyPanel.add(nameInput);
        JLabel fillerLab5 = new JLabel("");
        buyPanel.add(fillerLab5);
        JLabel fillerLab6 = new JLabel("");
        buyPanel.add(fillerLab6);
        JLabel quantityLab = new JLabel("Quantity");
        quantityLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField quantityInput = new JTextField(20);
        buyPanel.add(quantityLab);
        buyPanel.add(quantityInput);
        JLabel fillerLab7 = new JLabel("");
        buyPanel.add(fillerLab7);
        JButton buyButt = new JButton("Buy");
        buyPanel.add(buyButt);
        JLabel priceLab = new JLabel("Price");
        priceLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField priceInput = new JTextField(20);
        buyPanel.add(priceLab);
        buyPanel.add(priceInput);

        //sell panel - 2
        JPanel sellPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        sellPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        sellPanel.setBackground(new Color(255, 255, 255));
        JLabel symbolLab2 = new JLabel("Symbol");
        symbolLab2.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField symbolInput2 = new JTextField(20);
        sellPanel.add(symbolLab2);
        sellPanel.add(symbolInput2);
        JLabel fillerLab8 = new JLabel("");
        sellPanel.add(fillerLab8);
        JButton resetButt2 = new JButton("Reset");
        sellPanel.add(resetButt2);
        JLabel quantityLab2 = new JLabel("Quantity");
        quantityLab2.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField quantityInput2 = new JTextField(20);
        sellPanel.add(quantityLab2);
        sellPanel.add(quantityInput2);
        JLabel fillerLab9 = new JLabel("");
        sellPanel.add(fillerLab9);
        JButton sellButt = new JButton("Sell");
        sellPanel.add(sellButt);
        JLabel priceLab2 = new JLabel("Price");
        priceLab2.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField priceInput2 = new JTextField(20);
        sellPanel.add(priceLab2);
        sellPanel.add(priceInput2);

        //update panel - 3
        JPanel updatePanel = new JPanel(new GridLayout(3, 4, 10, 10));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        updatePanel.setBackground(new Color(255, 255, 255));
        JLabel symbolLab3 = new JLabel("Symbol");
        symbolLab3.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField symbolInput3 = new JTextField(20);
        symbolInput3.setEditable(false);
        updatePanel.add(symbolLab3);
        updatePanel.add(symbolInput3);
        JLabel fillerLab10 = new JLabel("");
        updatePanel.add(fillerLab10);
        JButton prevButt = new JButton("Prev");
        updatePanel.add(prevButt);
        JLabel nameLab3 = new JLabel("Name");
        nameLab3.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField nameInput3 = new JTextField(20);
        nameInput3.setEditable(false);
        updatePanel.add(nameLab3);
        updatePanel.add(nameInput3);
        JLabel fillerLab11 = new JLabel("");
        updatePanel.add(fillerLab11);
        JButton nextButt = new JButton("Next");
        updatePanel.add(nextButt);
        JLabel priceLab3 = new JLabel("Price");
        priceLab3.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField priceInput3 = new JTextField(20);
        updatePanel.add(priceLab3);
        updatePanel.add(priceInput3);
        JLabel fillerLab12 = new JLabel("");
        updatePanel.add(fillerLab12);
        JButton saveButt = new JButton("Save");
        updatePanel.add(saveButt);

        //gain panel - 4
        JPanel gainPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        gainPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        gainPanel.setBackground(new Color(255, 255, 255));
        JLabel totalLab = new JLabel("Total Gain");
        totalLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        gainPanel.add(totalLab);
        JTextField totalInput = new JTextField(20);
        totalInput.setEditable(false);
        gainPanel.add(totalInput);
        JLabel fillerLab17 = new JLabel("");
        gainPanel.add(fillerLab17);
        JLabel fillerLab18 = new JLabel("");
        gainPanel.add(fillerLab18);
        JLabel fillerLab19 = new JLabel("");
        gainPanel.add(fillerLab19);

        //search panel - 4
        JPanel searchPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        searchPanel.setBackground(new Color(255, 255, 255));
        JLabel symbolLab4 = new JLabel("Symbol");
        symbolLab4.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField symbolInput4 = new JTextField(20);
        searchPanel.add(symbolLab4);
        searchPanel.add(symbolInput4);
        JLabel fillerLab13 = new JLabel("");
        searchPanel.add(fillerLab13);
        JButton resetButt4 = new JButton("Reset");
        searchPanel.add(resetButt4);
        JLabel keywordsLab = new JLabel("Name Keywords");
        keywordsLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField keywordsInput = new JTextField(20);
        searchPanel.add(keywordsLab);
        searchPanel.add(keywordsInput);
        JLabel fillerLab14 = new JLabel("");
        searchPanel.add(fillerLab14);
        JLabel fillerLab15 = new JLabel("");
        searchPanel.add(fillerLab15);
        JLabel lowPriceLab = new JLabel("Low Price");
        lowPriceLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField lowPriceInput = new JTextField(20);
        searchPanel.add(lowPriceLab);
        searchPanel.add(lowPriceInput);
        JLabel fillerLab16 = new JLabel("");
        searchPanel.add(fillerLab16);
        JButton searchButt = new JButton("Search");
        searchPanel.add(searchButt);
        JLabel highPriceLab = new JLabel("High Price");
        highPriceLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JTextField highPriceInput = new JTextField(20);
        searchPanel.add(highPriceLab);
        searchPanel.add(highPriceInput);

        //messages
        JPanel messages = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel messagesLab = new JLabel("Messages: ");
        JTextArea messagesText = new JTextArea(10, 10);
        messagesLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        messages.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        messagesText.setBackground(new Color(244, 244, 244));
        messagesText.setEditable(false);
        messagesText.setLineWrap(false);
        JScrollPane scrollPane = new JScrollPane(messagesText);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        messages.setBackground(new Color(255, 255, 255));
        messages.add(messagesLab);
        messages.add(scrollPane);

        //individual gain - 2
        JPanel indivGain = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel indivGainLab = new JLabel("Individual Gain: ");
        JTextArea indivGainText = new JTextArea(10, 10);
        indivGainLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        indivGain.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        indivGainText.setBackground(new Color(244, 244, 244));
        indivGainText.setEditable(false);
        indivGainText.setLineWrap(false);
        JScrollPane scrollPane2 = new JScrollPane(indivGainText);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        indivGain.setBackground(new Color(255, 255, 255));
        indivGain.add(indivGainLab);
        indivGain.add(scrollPane2);

        //search results - 3
        JPanel searchRes = new JPanel(new GridLayout(2, 1, 10, 10));
        JLabel searchResLab = new JLabel("Search Results: ");
        JTextArea searchResText = new JTextArea(10, 10);
        searchResLab.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        searchRes.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        searchResText.setBackground(new Color(244, 244, 244));
        searchResText.setEditable(false);
        searchResText.setLineWrap(false);
        JScrollPane scrollPane3 = new JScrollPane(searchResText);
        scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        searchRes.setBackground(new Color(255, 255, 255));
        searchRes.add(searchResLab);
        searchRes.add(scrollPane3);

        this.add(commandsPanel);
        this.add(welcomePanel);

        //buy button on buy panel
        buyButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                messagesText.setText("");
                symbol = "";
                name = "";
                quantity = -10;
                price = -10;
                try {
                    symbol = portfolio.checkString(symbolInput.getText());
                } catch (PortfolioExceptions e) {
                    messagesText.append("There is an error with your symbol input!\n");
                }
                try {
                    name = portfolio.checkString(nameInput.getText());
                } catch (PortfolioExceptions e) {
                    messagesText.append("There is an error with your name input!\n");
                }
                try {
                    quantity = portfolio.checkInt(quantityInput.getText());
                } catch (PortfolioExceptions e) {
                    messagesText.append("There is an error with your quantity input!\n");
                }
                try {
                    price = portfolio.checkDouble(priceInput.getText());
                } catch (PortfolioExceptions e) {
                    messagesText.append("There is an error with your price input!\n");
                }

                if (!name.equals("") && !symbol.equals("") && price != -10 && quantity != -10) {
                    if (typeList.getSelectedItem().toString().equalsIgnoreCase("Stock")) {
                        messagesText.setText(portfolio.buy(list, name, symbol, price, quantity, "Stock"));
                    } else if (typeList.getSelectedItem().toString().equalsIgnoreCase("Mutual Fund")) {
                        messagesText.setText(portfolio.buy(list, name, symbol, price, quantity, "MF"));
                    }
                    //messagesText.append(portfolio.listInvestments(list));
                }
                if (list.size() > 0) {
                    last = list.size() - 1;
                }
            }

        });

        //reset button on buy panel
        resetButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                typeList.setSelectedIndex(0);
                symbolInput.setText("");
                nameInput.setText("");
                quantityInput.setText("");
                priceInput.setText("");
                messagesText.setText("");
            }
        });

        //reset button on sell panel
        resetButt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                symbolInput2.setText("");
                quantityInput2.setText("");
                priceInput2.setText("");
                messagesText.setText("");
            }
        });

        //reset button on search panel
        resetButt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                symbolInput4.setText("");
                keywordsInput.setText("");
                lowPriceInput.setText("");
                highPriceInput.setText("");
                searchResText.setText("");
            }
        });

        //save button on buy panel
        saveButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                messagesText.setText("");
                price = -10;
                symbol = "";
                symbol = symbolInput3.getText();
                try {
                    price = portfolio.checkDouble(priceInput3.getText());
                } catch (PortfolioExceptions e) {
                    messagesText.append("There is an error with your price input!\n");
                }
                if (price != -10 && !symbol.equals("")) {
                    messagesText.setText(portfolio.update(list, symbol, price, current));
                }
                messagesText.append(list.get(current).toString());

            }
        });

        //search button on search panel
        searchButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                searchResText.setText("");
                searchSymbol = "";
                searchKeywords = "";
                searchLow = -10;
                searchHigh = -10;

                if (!symbolInput4.getText().equals("")) {
                    searchSymbol = symbolInput4.getText();
                }
                if (!keywordsInput.getText().equals("")) {
                    searchKeywords = keywordsInput.getText();
                }
                if (!lowPriceInput.getText().equals("")) {
                    try {
                        searchLow = portfolio.checkDouble(lowPriceInput.getText());
                    } catch (PortfolioExceptions e) {
                        searchResText.append("There is an error with your price input!\n");
                    }
                }
                if (!highPriceInput.getText().equals("")) {
                    try {
                        searchHigh = portfolio.checkDouble(highPriceInput.getText());
                    } catch (PortfolioExceptions e) {
                        searchResText.append("There is an error with your price input!\n");
                    }
                }

                HashMap<String, ArrayList<Integer>> hashMap = portfolio.updateHash(list);
                searchResText.append(portfolio.search(list, hashMap, searchSymbol, searchKeywords, searchLow, searchHigh));

            }
        });

        //next button on update panel
        nextButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (list.size() > 0) {
                    last = list.size() - 1;
                }
                current = current + 1;
                if (current == first) {
                    prevButt.setEnabled(false);
                } else {
                    prevButt.setEnabled(true);
                }
                if (current == last) {
                    nextButt.setEnabled(false);
                } else {
                    nextButt.setEnabled(true);
                }
                symbolInput3.setText(list.get(current).symbol);
                nameInput3.setText(list.get(current).name);
            }
        });

        //previous button on update panel
        prevButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (list.size() > 0) {
                    last = list.size() - 1;
                }
                current = current - 1;
                if (current == first) {
                    prevButt.setEnabled(false);
                } else {
                    prevButt.setEnabled(true);
                }
                if (current == last) {
                    nextButt.setEnabled(false);
                } else {
                    nextButt.setEnabled(true);
                }
                symbolInput3.setText(list.get(current).symbol);
                nameInput3.setText(list.get(current).name);
            }
        });

        //sell button on sell panel
        sellButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                messagesText.setText("");
                symbol = "";
                name = "";
                quantity = -10;
                price = -10;
                if (list.isEmpty()) {
                    messagesText.setText("There are no investments to sell");
                } else {
                    try {
                        symbol = portfolio.checkString(symbolInput2.getText());
                    } catch (PortfolioExceptions e) {
                        messagesText.append("There is an error with your symbol input!\n");
                    }
                    try {
                        quantity = portfolio.checkInt(quantityInput2.getText());
                    } catch (PortfolioExceptions e) {
                        messagesText.append("There is an error with your quantity input!\n");
                    }
                    try {
                        price = portfolio.checkDouble(priceInput2.getText());
                    } catch (PortfolioExceptions e) {
                        messagesText.append("There is an error with your price input!\n");
                    }
                    if (!symbol.equals("") && quantity != -10 && price != -10) {
                        messagesText.setText(portfolio.sell(list, symbol, quantity, price));
                    }
                    //messagesText.append(portfolio.listInvestments(list));

                }
                if (list.size() > 0) {
                    last = list.size() - 1;
                }
            }
        });

        //commands menu buy button
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //remove welcome
                welcomePanel.setVisible(false);
                getContentPane().remove(welcomePanel);
                //remove sell
                sellPanel.setVisible(false);
                getContentPane().remove(sellPanel);
                sellingLab.setVisible(false);
                commandsPanel.remove(sellingLab);
                //remove update
                updatePanel.setVisible(false);
                getContentPane().remove(updatePanel);
                updatingLab.setVisible(false);
                commandsPanel.remove(updatingLab);
                //remove gain
                gainPanel.setVisible(false);
                getContentPane().remove(gainPanel);
                gainLab.setVisible(false);
                commandsPanel.remove(gainLab);
                //remove indivGain
                indivGain.setVisible(false);
                getContentPane().remove(indivGain);
                //remove search results
                searchRes.setVisible(false);
                getContentPane().remove(searchRes);
                //remove search
                searchPanel.setVisible(false);
                getContentPane().remove(searchPanel);
                searchLab.setVisible(false);
                commandsPanel.remove(searchLab);
                //remove searchRes
                searchRes.setVisible(false);
                getContentPane().remove(searchRes);
                //add buy
                buyPanel.setVisible(true);
                add(buyPanel);
                buyingLab.setVisible(true);
                commandsPanel.add(buyingLab);
                //add messages
                messages.setVisible(true);
                add(messages);
            }
        });

        //command menu option sell
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //remove welcome
                welcomePanel.setVisible(false);
                getContentPane().remove(welcomePanel);
                //remove buy
                buyPanel.setVisible(false);
                getContentPane().remove(buyPanel);
                buyingLab.setVisible(false);
                commandsPanel.remove(buyingLab);
                //remove gain
                gainPanel.setVisible(false);
                getContentPane().remove(gainPanel);
                gainLab.setVisible(false);
                commandsPanel.remove(gainLab);
                //remove individual gains
                indivGain.setVisible(false);
                getContentPane().remove(indivGain);
                //remove update
                updatePanel.setVisible(false);
                getContentPane().remove(updatePanel);
                updatingLab.setVisible(false);
                commandsPanel.remove(updatingLab);
                //remove search results
                searchRes.setVisible(false);
                getContentPane().remove(searchRes);
                //remove search
                searchPanel.setVisible(false);
                getContentPane().remove(searchPanel);
                searchLab.setVisible(false);
                commandsPanel.remove(searchLab);
                //add sell
                sellPanel.setVisible(true);
                add(sellPanel);
                sellingLab.setVisible(true);
                commandsPanel.add(sellingLab);
                //add messages
                messages.setVisible(true);
                add(messages);
            }
        });

        //command menu option update
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //remove welcome
                welcomePanel.setVisible(false);
                getContentPane().remove(welcomePanel);
                //remove buy
                buyPanel.setVisible(false);
                getContentPane().remove(buyPanel);
                buyingLab.setVisible(false);
                commandsPanel.remove(buyingLab);
                //remove sell
                sellPanel.setVisible(false);
                getContentPane().remove(sellPanel);
                sellingLab.setVisible(false);
                commandsPanel.remove(sellingLab);
                //remove gain
                gainPanel.setVisible(false);
                getContentPane().remove(gainPanel);
                gainLab.setVisible(false);
                commandsPanel.remove(gainLab);
                //remove individual gains
                indivGain.setVisible(false);
                getContentPane().remove(indivGain);
                //remove search results
                searchRes.setVisible(false);
                getContentPane().remove(searchRes);
                //remove search
                searchPanel.setVisible(false);
                getContentPane().remove(searchPanel);
                searchLab.setVisible(false);
                commandsPanel.remove(searchLab);
                //add update
                updatePanel.setVisible(true);
                saveButt.setEnabled(true);
                if (list.size() > 0) {
                    last = list.size() - 1;
                }
                if (current == first) {
                    prevButt.setEnabled(false);
                } else {
                    prevButt.setEnabled(true);
                }
                if (current == last) {
                    nextButt.setEnabled(false);
                } else {
                    nextButt.setEnabled(true);
                }
                if (list.size() > 0) {
                    symbolInput3.setText(list.get(current).symbol);
                    nameInput3.setText(list.get(current).name);
                } else {
                    messagesText.setText("There are no investments in the list");
                    saveButt.setEnabled(false);
                }
                add(updatePanel);
                updatingLab.setVisible(true);
                commandsPanel.add(updatingLab);

                //add messages
                messages.setVisible(true);
                add(messages);

            }
        });

        //command menu option get gain
        getGain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //remove welcome
                welcomePanel.setVisible(false);
                getContentPane().remove(welcomePanel);
                //remove buy
                buyPanel.setVisible(false);
                getContentPane().remove(buyPanel);
                buyingLab.setVisible(false);
                commandsPanel.remove(buyingLab);
                //remove sell
                sellPanel.setVisible(false);
                getContentPane().remove(sellPanel);
                sellingLab.setVisible(false);
                commandsPanel.remove(sellingLab);
                //remove update
                updatePanel.setVisible(false);
                getContentPane().remove(updatePanel);
                updatingLab.setVisible(false);
                commandsPanel.remove(updatingLab);
                //remove messages
                messages.setVisible(false);
                getContentPane().remove(messages);
                //remove search results
                searchRes.setVisible(false);
                getContentPane().remove(searchRes);
                //remove search
                searchPanel.setVisible(false);
                getContentPane().remove(searchPanel);
                searchLab.setVisible(false);
                commandsPanel.remove(searchLab);
                //add gain
                gainPanel.setVisible(true);
                add(gainPanel);
                gainLab.setVisible(true);
                totalInput.setText(portfolio.getTotalGain(list));
                commandsPanel.add(gainLab);
                //add indivGain
                indivGain.setVisible(true);
                indivGainText.setText(portfolio.getIndividualGain(list));
                add(indivGain);

            }
        });

        //command menu option search
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //remove welcome
                welcomePanel.setVisible(false);
                getContentPane().remove(welcomePanel);
                //remove buy
                buyPanel.setVisible(false);
                getContentPane().remove(buyPanel);
                buyingLab.setVisible(false);
                commandsPanel.remove(buyingLab);
                //remove sell
                sellPanel.setVisible(false);
                getContentPane().remove(sellPanel);
                sellingLab.setVisible(false);
                commandsPanel.remove(sellingLab);
                //remove update
                updatePanel.setVisible(false);
                getContentPane().remove(updatePanel);
                updatingLab.setVisible(false);
                commandsPanel.remove(updatingLab);
                //remove gain
                gainPanel.setVisible(false);
                getContentPane().remove(gainPanel);
                gainLab.setVisible(false);
                commandsPanel.remove(gainLab);
                //remove individual gain
                indivGain.setVisible(false);
                getContentPane().remove(indivGain);
                //remove messages
                messages.setVisible(false);
                getContentPane().remove(messages);
                //add search
                searchPanel.setVisible(true);
                add(searchPanel);
                searchLab.setVisible(true);
                commandsPanel.add(searchLab);
                //add search results
                searchRes.setVisible(true);
                add(searchRes);

            }
        });

        //command menu quit option - write to file and quit program
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(portfolio.writeFile(list, fileName));
                System.exit(0);

            }
        });

        //when user clicks x, write to file and end program
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println(portfolio.writeFile(list, fileName));
                System.exit(0);

            }
        });

    }

}
