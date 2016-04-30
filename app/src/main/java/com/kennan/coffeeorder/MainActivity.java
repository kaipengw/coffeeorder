package com.kennan.coffeeorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int quantity = 0;
    private double price = 0d;
    private String summaryMessage = null;
    private boolean isWhippedCreamToppingChecked = false;
    private boolean isChocolateToppingChecked = false;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(quantity + "");
    }

    private void generateMessage() {
        TextView nameEditText = (TextView) findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString().trim();
        if (name == null) {
            return;
        } else {
            String nameOutput = getContext().getString(R.string.order_summary_name)+ name + "\n";
            String addChocolate = getContext().getString(R.string.order_summary_chocolate) + "? " + isChocolateToppingChecked + "\n";
            String addWhippedCream = getContext().getString(R.string.order_summary_whipped_cream) + "? " + isWhippedCreamToppingChecked + "\n";
            String priceOutput = "Total: $" + price + "\n";
            String thankyouOutput = getContext().getString(R.string.thank_you) + "\n";
            summaryMessage = nameOutput + addChocolate + addWhippedCream + priceOutput + thankyouOutput;
        }
    }

    private void displayPrice() {
        TextView orderSummary = (TextView) findViewById(R.id.order_summary_text_view);
        generateMessage();
        orderSummary.setText(summaryMessage);


    }
    public void order(View view) {
        price = 5 * quantity + (isWhippedCreamToppingChecked ? 1.5d : 0d) + (isChocolateToppingChecked ? 2.5d : 0d);
        displayPrice();
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
        }
    }

    public void emailTheOrder(View view) {
        EditText nameInput = (EditText) findViewById(R.id.name_edit_text);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for " + nameInput.getText().toString().trim() +".");
        emailIntent.putExtra(Intent.EXTRA_TEXT, summaryMessage);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}
