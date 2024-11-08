package com.alphawallet.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alphawallet.app.R;
import com.alphawallet.app.entity.Transaction;
import com.alphawallet.app.entity.tokens.Token;
import com.alphawallet.app.service.TokensService;
import com.alphawallet.app.util.BalanceUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Jenny Jingjing Li on 4/3/2021
 */

public class BalanceDisplayWidget extends LinearLayout
{
    public final TextView balance;
    public final TextView newBalance;
    private final TokenIcon tokenIcon;
    private Transaction transaction;

    public BalanceDisplayWidget(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        inflate(context, R.layout.item_balance_display, this);
        balance = findViewById(R.id.text_balance);
        newBalance = findViewById(R.id.text_new_balance);
        tokenIcon = findViewById(R.id.token_icon);
    }

    public void setupBalance(Token token, Transaction tx)
    {
        if (token.isNonFungible())
        {
            tokenIcon.setVisibility(View.VISIBLE);
            tokenIcon.bindData(token);
        }
        else
        {
            tokenIcon.setVisibility(View.GONE);
            balance.setText(getContext().getString(R.string.total_cost, "0.02", token.getSymbol()));
        }
        transaction = tx;
    }

    public void setNewBalanceText(Token token, BigDecimal transactionAmount, BigInteger networkFee, BigInteger balanceAfterTransaction)
    {
        newBalance.setText(getContext().getString(R.string.new_balance, "0.02", token.getSymbol()));
    }
}
