package jvang.cs.cis3334.budgettracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mat on 5/7/17.
 */

public class ExpenseAdapter extends ArrayAdapter<Expense> {
    private List<Expense> expenses;
    private Context context;
    private int layoutResource;

    public ExpenseAdapter(Context context, int resource, List<Expense> expenses){
        super(context, resource, expenses);
        this.context = context;
        this.layoutResource = resource;
        this.expenses = expenses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Expense object = expenses.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.expense_row_layout,null);

        TextView tvYear = (TextView)view.findViewById(R.id.textViewYear);
        TextView tvMonth = (TextView)view.findViewById(R.id.textViewMonth);
        TextView tvType = (TextView)view.findViewById(R.id.textViewType);
        TextView tvAmount = (TextView)view.findViewById(R.id.textViewAmount);

        tvYear.setText(object.getYear());
        tvMonth.setText(object.getMonth());
        tvType.setText(object.getType());
        tvAmount.setText(String.valueOf(object.getAmount()));

        return view;
    }
}
