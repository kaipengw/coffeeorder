package com.kennan.coffeeorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int quantity = 0;
    private double price = 0d;
    private double toppingPrice = 0d;
    private boolean isWhippedCreamToppingChecked = false;
    private boolean isChocolateToppingChecked = false;

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
        String addChocolate = "Add chocolate? " + isChocolateToppingChecked + "\n";
        String addWhippedCream = "Add whipped cream? " + isWhippedCreamToppingChecked + "\n";
        String nameOutput = "Name: Lyla John\n";
        String priceOutput = "Total: $" + price + "\n";
        String thankyouOutput = "Thank you!\n";
        orderSummary.setText(addChocolate + addWhippedCream + nameOutput + priceOutput + thankyouOutput);

    }
    public void order(View view) {
        displayPrice(5 * quantity + (isWhippedCreamToppingChecked ? 1.5d : 0d) + (isChocolateToppingChecked ? 2.5d : 0d));
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
//        isWhippedCreamToppingChecked = !isWhippedCreamToppingChecked;
        boolean isChecked = ((CheckBox) view).isChecked();
        Log.i(this.getClass().toString(), view.toString());
        switch (view.getId()) {
            case R.id.chocolate_topping_check_box:
                if (isChecked) {
                    isChocolateToppingChecked = true;
                } else {
                    isChocolateToppingChecked = false;
                }
                break;
            case R.id.whipped_cream_topping_check_box:
                if (isChecked) {
                    isWhippedCreamToppingChecked = true;
                } else {
                    isWhippedCreamToppingChecked = false;
                }
                break;
            default:
                return;
        }
    }
}
