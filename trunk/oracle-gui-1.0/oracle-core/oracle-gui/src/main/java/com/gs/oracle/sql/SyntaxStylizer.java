/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * 
 * The Original Code is iSQL-Viewer, A Mutli-Platform Database Tool.
 *
 * The Initial Developer of the Original Code is iSQL-Viewer, A Mutli-Platform Database Tool.
 * Portions created by Mark A. Kobold are Copyright (C) 2000-2007. All Rights Reserved.
 *
 * Contributor(s): 
 *  Mark A. Kobold [mkobold <at> isqlviewer <dot> com].
 *  
 * If you didn't download this code from the following link, you should check
 * if you aren't using an obsolete version: http://www.isqlviewer.com
 */
package com.gs.oracle.sql;

import java.awt.Color;
import java.awt.Font;
import java.util.Hashtable;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import com.gs.oracle.sql.processor.TokenType;


/**
 * @author Mark A. Kobold &lt;mkobold at isqlviewer dot com&gt;
 * @version 1.0
 */
public class SyntaxStylizer implements PreferenceChangeListener {

    private Hashtable<TokenType, Style> styleMap = new Hashtable<TokenType, Style>();
    private JTextPane componentOwner = null;

    public SyntaxStylizer(JTextPane componentOwner) {

        this.componentOwner = componentOwner;
        initializeStyles();
    }

    public Style styleForWord(TokenType tokenType) {

        Style style = styleMap.get(tokenType);
        if (style == null) {
            return styleMap.get(TokenType.WHITESPACE);
        }
        return style;
    }

    public void preferenceChange(PreferenceChangeEvent event) {

    }

    public void changeStyle(TokenType type, Color color) {

        Style style = componentOwner.addStyle(type.toString(), null);
        StyleConstants.setForeground(style, color);
        styleMap.put(type, style);
    }

    public void changeStyle(TokenType type, Color color, int fontStyle) {

        Style style = componentOwner.addStyle(type.toString(), null);
        StyleConstants.setForeground(style, color);
        if ((fontStyle & Font.BOLD) != 0)
            StyleConstants.setBold(style, true);
        if ((fontStyle & Font.ITALIC) != 0)
            StyleConstants.setItalic(style, true);
        styleMap.put(type, style);
    }

    private void initializeStyles() {

        changeStyle(TokenType.UNRECOGNIZED, Color.RED);
        changeStyle(TokenType.WHITESPACE, Color.BLACK);
        changeStyle(TokenType.WORD, Color.BLACK);

        changeStyle(TokenType.COMMENT, Color.decode("#3F7F5F"), Font.ITALIC);
        changeStyle(TokenType.START_COMMENT, Color.decode("#3F7F5F"), Font.ITALIC);
        changeStyle(TokenType.MID_COMMENT, Color.decode("#3F7F5F"), Font.ITALIC);
        changeStyle(TokenType.END_COMMENT, Color.decode("#3F7F5F"), Font.ITALIC);

        changeStyle(TokenType.VARIABLE, Color.decode("#003e85"), Font.ITALIC | Font.BOLD);

        changeStyle(TokenType.KEYWORD, Color.decode("#7F0069"), Font.BOLD);
        changeStyle(TokenType.STRING, Color.BLUE);
        changeStyle(TokenType.CHARACTER, Color.decode("#008400"));
        changeStyle(TokenType.FUNCTION, Color.BLUE);

        changeStyle(TokenType.TABLE_NAME, Color.decode("#003e85"));

        Style style = componentOwner.addStyle(TokenType.TABLE_NAME.name(), null);
        StyleConstants.setForeground(style, Color.decode("#3f7f5f"));
        StyleConstants.setUnderline(style, true);
        style.addAttribute("hyperlinked", Boolean.TRUE);

        styleMap.put(TokenType.TABLE_NAME, style);
    }

}