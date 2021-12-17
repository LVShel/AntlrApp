package com.shelest.antlrApp;

import com.shelest.antlrApp.generated.GrammarBaseVisitor;
import com.shelest.antlrApp.generated.GrammarLexer;
import com.shelest.antlrApp.generated.GrammarParser;
import com.shelest.antlrApp.generated.GrammarParser.AdditiveExprContext;
import com.shelest.antlrApp.generated.GrammarParser.ExponentialExprContext;
import com.shelest.antlrApp.generated.GrammarParser.MultiplicativeExprContext;
import com.shelest.antlrApp.generated.GrammarParser.NumberExprContext;
import com.shelest.antlrApp.generated.GrammarParser.ParenthesizedExprContext;

public class VisitorClass extends GrammarBaseVisitor<Double> {

    @Override
    public Double visitMultiplicativeExpr(MultiplicativeExprContext ctx) {
        double left = super.visit(ctx.expression(0));
        double right = super.visit(ctx.expression(1));
        if (ctx.operatorToken.getType() == GrammarLexer.MULTIPLY)
            return left * right;
        else return left / right;
    }

    @Override
    public Double visitExponentialExpr(ExponentialExprContext ctx) {
        double left = super.visit(ctx.expression(0));
        double right = super.visit(ctx.expression(1));
        return Math.pow(left, right);
    }

    @Override
    public Double visitAdditiveExpr(AdditiveExprContext ctx) {
        double left = visit(ctx.expression(0));
        double right = visit(ctx.expression(1));
        if (ctx.operatorToken.getType() == GrammarLexer.ADD)
            return left + right;
        else return left - right;
    }

    @Override
    public Double visitNumberExpr(NumberExprContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Double visitParenthesizedExpr(ParenthesizedExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Double visitSquareRootExpression(GrammarParser.SquareRootExpressionContext ctx) {
        double value = visit(ctx.expression());
        return Math.sqrt(value);
    }

}
