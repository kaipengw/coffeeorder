package com.kennan.coffeeorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int quantity = 0;
    private double price = 0d;
    private double toppingPrice = 0d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(quantity + "");
    }

    private void displayPrice(double price) {
        TextView orderSummary = (TextView) findViewById(R.id.order_summary_text_view);
        String displayedText = "Name: Lyla\nTotal: $" + price + "\nThank you!";
        orderSummary.setText(displayedText);

    }
    public void order(View view) {
        displayPrice(5 * quantity + toppingPrice);
    }

    public void minusOne(View view) {
        if (quantity == 0) {
            return;
        } else {
            quantity--;
        }
        displayQuantity(quantity);
    }

    public void plusOne(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    public void onClickTopping(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.topping_check_box:
                if (checked) {
                    toppingPrice += 1.5d;
                } else {
                    toppingPrice = 0d;
                }
                return;
            default:
                return;
        }

    }
}
