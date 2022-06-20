package ru.nsu.ccfit.lopatkin.client.utils;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BubbleMessages extends Group{
    public static final String ARIAL = "Arial";
    public static final int BIG_SIZE = 18;
    public static final int SMALL_SIZE = 12;
    public static final int SHIFT = 2;
    private static int p = 14;
    private static int s = SHIFT;
    private static int pm = 10;
    private int sm = SHIFT;
    private Font textFont = Font.font(ARIAL, BIG_SIZE);
    private Paint textColor = Color.WHITE;
    private Font metaFont = Font.font(ARIAL, SMALL_SIZE);
    private Paint metaColor = Color.LIGHTGRAY;

    private Rectangle r;

    private static Paint bubbleColor = Color.rgb(0, 126, 229);

    private static int edgeRadius = 30;


    public BubbleMessages(String text) {
        super();
        init(0, 0, text, "");
    }


    public BubbleMessages(String text, String meta, boolean quadratic) {
        super();
        if(quadratic) {
            init(0, 0, text, meta);
            r.setHeight(r.getWidth());
        }
        else
            init(0, 0, text, meta);
    }


    public BubbleMessages(String text, boolean quadratic) {
        super();
        if(quadratic) {
            init(0, 0, text, "");
            r.setHeight(r.getWidth());
        }
        else
            init(0, 0, text, "");
    }


    public BubbleMessages(String text, String meta) {
        super();
        init(0, 0, text, meta);
    }


    public BubbleMessages(int x, int y, String text, String meta) {
        super();
        init(x, y, text, meta);
    }

    private void init(int x, int y, String text, String meta) {
        // temp for text
        Text temp = new Text(text);
        temp.setFont(textFont);
        int textW = (int) temp.getLayoutBounds().getWidth();
        int textH = (int) temp.getLayoutBounds().getHeight();
        int w = textW + p * SHIFT + s * SHIFT;
        int h = textH + p * SHIFT;
        temp = null;

        // tmp for meta
        Text tmp = new Text(meta);
        tmp.setFont(metaFont);
        int metaW = (int) tmp.getLayoutBounds().getWidth();
        int metaH = (int) tmp.getLayoutBounds().getHeight();
        h += metaH;

        if (metaW > textW)  w = metaW + p * SHIFT + s * SHIFT;

        // label text
        Label l = new Label(text);
        l.setFont(textFont);
        l.setTextFill(textColor);
        l.setTranslateX(x + p + s);
        l.setTranslateY(y + p);

        // label meta
        Label m = new Label(meta);
        m.setFont(metaFont);
        m.setTextFill(metaColor);
        m.setTranslateX(x + (w - (metaW + pm + sm)));
        m.setTranslateY(y + textH + pm * SHIFT);

        // bubble
        r = new Rectangle();
        r.setTranslateX(x);
        r.setTranslateY(y);

        r.setWidth(w);
        r.setHeight(h);

        r.setArcHeight(this.edgeRadius);
        r.setArcWidth(this.edgeRadius);

        r.setFill(bubbleColor);

        getChildren().addAll(r, l, m);
    }



    public void setWidth(double width) {
        r.setWidth(width);
    }


    public void setHeight(double height) {
        r.setWidth(height);
    }


    public void setQuadraticSize(double size) {
        r.setWidth(size);
        r.setHeight(size);
    }


    public void setEdgeRadius(int radius) {
        this.edgeRadius = radius;
    }


    public int getEdgeRadius() {
        return this.edgeRadius;
    }

    public int getTextPadding() {
        return p;
    }

    public void setTextPadding(int textPadding) {
        this.p = textPadding;
    }

    public int getTextSidespace() {
        return s;
    }

    public void setTextSidespace(int textSidespace) {
        this.s = textSidespace;
    }

    public int getMetaPadding() {
        return pm;
    }


    public void setMetaPadding(int metaPadding) {
        this.pm = metaPadding;
    }


    public int getMetaSidespace() {
        return sm;
    }


    public void setMetaSidespace(int metaSidespace) {
        this.sm = metaSidespace;
    }


    public Font getTextFont() {
        return textFont;
    }


    public void setTextFont(Font textFont) {
        this.textFont = textFont;
    }


    public Paint getTextColor() {
        return textColor;
    }


    public void setTextColor(Paint textColor) {
        this.textColor = textColor;
    }


    public Font getMetaFont() {
        return metaFont;
    }


    public void setMetaFont(Font metaFont) {
        this.metaFont = metaFont;
    }


    public Paint getMetaColor() {
        return metaColor;
    }


    public void setMetaColor(Paint metaColor) {
        this.metaColor = metaColor;
    }


    public Paint getBubbleColor() {
        return bubbleColor;
    }


    public void setBubbleColor(Paint bubbleColor) {
        this.bubbleColor = bubbleColor;
    }
}
