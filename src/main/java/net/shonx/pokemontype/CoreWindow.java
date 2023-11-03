/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <https://unlicense.org>
 */

package net.shonx.pokemontype;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class CoreWindow extends JFrame {

    private static final long serialVersionUID = 3840829536588681981L;
    private static final Font COURIER_BOLD = new Font("Courier New", Font.BOLD, 18);
    private static final Font COURIER_PLAIN = new Font("Courier New", Font.PLAIN, 14);
    private static final Dimension DEFAULT_DIMENSION = new Dimension(172, 81);
    private static final Dimension BIG_WINDOW_DIMENSION = new Dimension(400, 500);

    private JLabel effectiveLabel;
    private JScrollPane effectiveScroll;
    private JTextArea effectiveText;
    private JLabel immuneLabel;
    private JScrollPane immuneScroll;
    private JTextArea immuneText;
    private JLabel resistsLabel;
    private JScrollPane resistsScroll;
    private JTextArea resistsText;
    private JLabel superEffectiveLabel;
    private JScrollPane superEffectiveScroll;
    private JTextArea superEffectiveText;
    private JLabel type1Label;
    private JComboBox<PokemonType> type1Selection;
    private JLabel type2Label;
    private JComboBox<PokemonType> type2Selection;
    private JLabel ultraEffectiveLabel;
    private JScrollPane ultraEffectiveScroll;
    private JTextArea ultraEffectiveText;
    private JLabel ultraResistsLabel;
    private JScrollPane ultraResistsScroll;
    private JTextArea ultraResistsText;

    private boolean stillLoading = true;

    /**
     * Creates new form Window
     */
    public CoreWindow() {
        initComponents();
        setLocationRelativeTo(null);
        populateSelection();
        buildLayout();
        setVisible(true);
        stillLoading = false;
        type1Selection.setSelectedIndex(0);
    }

    private void initComponents() {

        type1Label = new JLabel();
        type2Label = new JLabel();
        type1Selection = new JComboBox<>();
        type2Selection = new JComboBox<>();
        immuneLabel = new JLabel();
        immuneScroll = new JScrollPane();
        immuneText = new JTextArea();
        ultraResistsLabel = new JLabel();
        ultraResistsScroll = new JScrollPane();
        ultraResistsText = new JTextArea();
        resistsLabel = new JLabel();
        resistsScroll = new JScrollPane();
        resistsText = new JTextArea();
        effectiveLabel = new JLabel();
        effectiveScroll = new JScrollPane();
        effectiveText = new JTextArea();
        superEffectiveLabel = new JLabel();
        ultraEffectiveLabel = new JLabel();
        superEffectiveScroll = new JScrollPane();
        superEffectiveText = new JTextArea();
        ultraEffectiveScroll = new JScrollPane();
        ultraEffectiveText = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pokémon Typing");
        setMaximumSize(BIG_WINDOW_DIMENSION);
        setMinimumSize(BIG_WINDOW_DIMENSION);
        setName("coreFrame");
        setResizable(false);
        setSize(BIG_WINDOW_DIMENSION);

        type1Label.setFont(COURIER_BOLD);
        type1Label.setHorizontalAlignment(SwingConstants.CENTER);
        type1Label.setText("Type 1");
        type1Label.setToolTipText("");
        type1Label.setHorizontalTextPosition(SwingConstants.CENTER);

        type2Label.setFont(COURIER_BOLD);
        type2Label.setHorizontalAlignment(SwingConstants.CENTER);
        type2Label.setText("Type 2");
        type2Label.setToolTipText("");
        type2Label.setHorizontalTextPosition(SwingConstants.CENTER);

        type1Selection.setFont(COURIER_PLAIN);
        type1Selection.setActionCommand("type1Changed");
        type1Selection.addActionListener(this::recalculate);

        type2Selection.setFont(COURIER_PLAIN);
        type2Selection.setActionCommand("type1Changed");
        type2Selection.addActionListener(this::recalculate);

        immuneLabel.setFont(COURIER_BOLD);
        immuneLabel.setHorizontalAlignment(SwingConstants.LEFT);
        immuneLabel.setText("IMMUNE TO");
        immuneLabel.setToolTipText("");
        immuneLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        immuneScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        immuneScroll.setMaximumSize(DEFAULT_DIMENSION);
        immuneScroll.setMinimumSize(DEFAULT_DIMENSION);
        immuneScroll.setPreferredSize(DEFAULT_DIMENSION);

        immuneText.setEditable(false);
        immuneText.setColumns(20);
        immuneText.setFont(COURIER_PLAIN);
        immuneText.setRows(5);
        immuneText.setMaximumSize(DEFAULT_DIMENSION);
        immuneText.setMinimumSize(DEFAULT_DIMENSION);
        immuneScroll.setViewportView(immuneText);

        ultraResistsLabel.setFont(COURIER_BOLD);
        ultraResistsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        ultraResistsLabel.setText("ULTRA RESISTS");
        ultraResistsLabel.setToolTipText("");
        ultraResistsLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        ultraResistsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ultraResistsScroll.setMaximumSize(DEFAULT_DIMENSION);
        ultraResistsScroll.setMinimumSize(DEFAULT_DIMENSION);
        ultraResistsScroll.setPreferredSize(DEFAULT_DIMENSION);

        ultraResistsText.setEditable(false);
        ultraResistsText.setColumns(20);
        ultraResistsText.setFont(COURIER_PLAIN);
        ultraResistsText.setRows(5);
        ultraResistsText.setMaximumSize(DEFAULT_DIMENSION);
        ultraResistsText.setMinimumSize(DEFAULT_DIMENSION);
        ultraResistsScroll.setViewportView(ultraResistsText);

        resistsLabel.setFont(COURIER_BOLD);
        resistsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        resistsLabel.setText("RESISTS");
        resistsLabel.setToolTipText("");
        resistsLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        resistsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        resistsScroll.setMaximumSize(DEFAULT_DIMENSION);
        resistsScroll.setMinimumSize(DEFAULT_DIMENSION);
        resistsScroll.setPreferredSize(DEFAULT_DIMENSION);

        resistsText.setEditable(false);
        resistsText.setColumns(20);
        resistsText.setFont(COURIER_PLAIN);
        resistsText.setRows(5);
        resistsText.setMaximumSize(DEFAULT_DIMENSION);
        resistsText.setMinimumSize(DEFAULT_DIMENSION);
        resistsScroll.setViewportView(resistsText);

        effectiveLabel.setFont(COURIER_BOLD);
        effectiveLabel.setHorizontalAlignment(SwingConstants.LEFT);
        effectiveLabel.setText("EFFECTIVE");
        effectiveLabel.setToolTipText("");
        effectiveLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        effectiveScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        effectiveScroll.setMaximumSize(DEFAULT_DIMENSION);
        effectiveScroll.setMinimumSize(DEFAULT_DIMENSION);
        effectiveScroll.setPreferredSize(DEFAULT_DIMENSION);

        effectiveText.setEditable(false);
        effectiveText.setColumns(20);
        effectiveText.setFont(COURIER_PLAIN);
        effectiveText.setRows(5);
        effectiveText.setMaximumSize(DEFAULT_DIMENSION);
        effectiveText.setMinimumSize(DEFAULT_DIMENSION);
        effectiveScroll.setViewportView(effectiveText);

        superEffectiveLabel.setFont(COURIER_BOLD);
        superEffectiveLabel.setHorizontalAlignment(SwingConstants.LEFT);
        superEffectiveLabel.setText("SUPER EFFECTIVE");
        superEffectiveLabel.setToolTipText("");
        superEffectiveLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        ultraEffectiveLabel.setFont(COURIER_BOLD);
        ultraEffectiveLabel.setHorizontalAlignment(SwingConstants.LEFT);
        ultraEffectiveLabel.setText("ULTRA EFFECTIVE");
        ultraEffectiveLabel.setToolTipText("");
        ultraEffectiveLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        superEffectiveScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        superEffectiveScroll.setMaximumSize(DEFAULT_DIMENSION);
        superEffectiveScroll.setMinimumSize(DEFAULT_DIMENSION);
        superEffectiveScroll.setPreferredSize(DEFAULT_DIMENSION);

        superEffectiveText.setEditable(false);
        superEffectiveText.setColumns(20);
        superEffectiveText.setFont(COURIER_PLAIN);
        superEffectiveText.setRows(5);
        superEffectiveText.setMaximumSize(DEFAULT_DIMENSION);
        superEffectiveText.setMinimumSize(DEFAULT_DIMENSION);
        superEffectiveScroll.setViewportView(superEffectiveText);

        ultraEffectiveScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ultraEffectiveScroll.setMaximumSize(DEFAULT_DIMENSION);
        ultraEffectiveScroll.setMinimumSize(DEFAULT_DIMENSION);
        ultraEffectiveScroll.setPreferredSize(DEFAULT_DIMENSION);

        ultraEffectiveText.setEditable(false);
        ultraEffectiveText.setColumns(20);
        ultraEffectiveText.setFont(COURIER_PLAIN);
        ultraEffectiveText.setRows(5);
        ultraEffectiveText.setMaximumSize(DEFAULT_DIMENSION);
        ultraEffectiveText.setMinimumSize(DEFAULT_DIMENSION);
        ultraEffectiveScroll.setViewportView(ultraEffectiveText);

    }

    // @formatter:off
    private void buildLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(type1Selection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(type1Label))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(type2Selection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(type2Label))
                .addGap(108, 108, 108))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(resistsLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(immuneLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ultraResistsLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                    .addComponent(resistsScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(effectiveLabel, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                    .addComponent(superEffectiveLabel, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                    .addComponent(ultraEffectiveLabel, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                    .addComponent(ultraEffectiveScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(immuneScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(effectiveScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ultraResistsScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(superEffectiveScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(type1Label)
                    .addComponent(type2Label))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(type1Selection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(type2Selection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(immuneLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(effectiveLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(immuneScroll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addComponent(effectiveScroll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(ultraResistsLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(superEffectiveLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(ultraResistsScroll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addComponent(superEffectiveScroll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resistsLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resistsScroll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ultraEffectiveLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ultraEffectiveScroll, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }
    // @formatter:on

    private void populateSelection() {
        type1Selection.addItem(PokemonType.NORMAL);
        type1Selection.addItem(PokemonType.FIGHTING);
        type1Selection.addItem(PokemonType.FLYING);
        type1Selection.addItem(PokemonType.POSION);
        type1Selection.addItem(PokemonType.GROUND);
        type1Selection.addItem(PokemonType.ROCK);
        type1Selection.addItem(PokemonType.BUG);
        type1Selection.addItem(PokemonType.GHOST);
        type1Selection.addItem(PokemonType.STEEL);
        type1Selection.addItem(PokemonType.FIRE);
        type1Selection.addItem(PokemonType.WATER);
        type1Selection.addItem(PokemonType.GRASS);
        type1Selection.addItem(PokemonType.ELECTRIC);
        type1Selection.addItem(PokemonType.PSYCHIC);
        type1Selection.addItem(PokemonType.ICE);
        type1Selection.addItem(PokemonType.DRAGON);
        type1Selection.addItem(PokemonType.DARK);
        type1Selection.addItem(PokemonType.FAIRY);

        type1Selection.setSelectedItem(PokemonType.NORMAL);

        type2Selection.addItem(PokemonType.NONE);
        type2Selection.addItem(PokemonType.NORMAL);
        type2Selection.addItem(PokemonType.FIGHTING);
        type2Selection.addItem(PokemonType.FLYING);
        type2Selection.addItem(PokemonType.POSION);
        type2Selection.addItem(PokemonType.GROUND);
        type2Selection.addItem(PokemonType.ROCK);
        type2Selection.addItem(PokemonType.BUG);
        type2Selection.addItem(PokemonType.GHOST);
        type2Selection.addItem(PokemonType.STEEL);
        type2Selection.addItem(PokemonType.FIRE);
        type2Selection.addItem(PokemonType.WATER);
        type2Selection.addItem(PokemonType.GRASS);
        type2Selection.addItem(PokemonType.ELECTRIC);
        type2Selection.addItem(PokemonType.PSYCHIC);
        type2Selection.addItem(PokemonType.ICE);
        type2Selection.addItem(PokemonType.DRAGON);
        type2Selection.addItem(PokemonType.DARK);
        type2Selection.addItem(PokemonType.FAIRY);

        type2Selection.setSelectedItem(PokemonType.NONE);

    }

    private void recalculate(ActionEvent evt) {
        if (stillLoading)
            return; // NullPointerException be like
        HashMap<Effectiveness, ArrayList<PokemonType>> map = new HashMap<Effectiveness, ArrayList<PokemonType>>(6);
        map.put(Effectiveness.IMMUNE, new ArrayList<PokemonType>(16));
        map.put(Effectiveness.ULTRA_RESISTS, new ArrayList<PokemonType>(16));
        map.put(Effectiveness.RESISTS, new ArrayList<PokemonType>(16));
        map.put(Effectiveness.EFFECTIVE, new ArrayList<PokemonType>(16));
        map.put(Effectiveness.SUPER_EFFECTIVE, new ArrayList<PokemonType>(16));
        map.put(Effectiveness.ULTRA_EFFECTIVE, new ArrayList<PokemonType>(16));

        Object selected1 = type1Selection.getSelectedItem();
        Object selected2 = type2Selection.getSelectedItem();
        if (!(selected1 instanceof PokemonType)) {
            throw new NullPointerException("type1");
        }
        PokemonType type1 = (PokemonType) selected1;

        if (!(selected2 instanceof PokemonType)) {
            throw new NullPointerException("type2");
        }
        PokemonType type2 = (PokemonType) selected2;

        if (type1 == type2) {
            type2Selection.setSelectedIndex(0);
            return;
        }

        for (PokemonType type : PokemonType.values()) {
            if (type == PokemonType.NONE)
                continue;
            double damage;
            if (type2 != PokemonType.NONE)
                damage = PokemonType.getMatchup(type, type1) * PokemonType.getMatchup(type, type2);
            else
                damage = PokemonType.getMatchup(type, type1);
            map.get(Effectiveness.get(damage)).add(type);
        }

        immuneText.setText(buildString(map.get(Effectiveness.IMMUNE)));
        ultraResistsText.setText(buildString(map.get(Effectiveness.ULTRA_RESISTS)));
        resistsText.setText(buildString(map.get(Effectiveness.RESISTS)));
        effectiveText.setText(buildString(map.get(Effectiveness.EFFECTIVE)));
        superEffectiveText.setText(buildString(map.get(Effectiveness.SUPER_EFFECTIVE)));
        ultraEffectiveText.setText(buildString(map.get(Effectiveness.ULTRA_EFFECTIVE)));
    }

    private String buildString(ArrayList<PokemonType> typeList) {
        StringBuilder builder = new StringBuilder();
        for (PokemonType type : typeList)
            builder.append(type.name() + System.lineSeparator());
        return builder.toString();

    }
}
