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
 * Created by jvang5 on 5/7/17.
 * This is a custom made adapter. This will determine how the items will look in the listView. It is
 * a simiple format: year   month   type    amount.
 */
public class ExpenseAdapter extends ArrayAdapter<Expense> {
    // Declaring variables
    private List<Expense> expenses;
    private Context context;
    private int layoutResource;

    /**
     * Constructor
     */
    public ExpenseAdapter(Context context, int resource, List<Expense> expenses){
        super(context, resource, expenses);
        this.context = context;
        this.layoutResource = resource;
        this.expenses = expenses;
    }

    /**
     * This is the method the listView will call when it needs to set up the rows. This will set the
     * rows to the format mentioned aboved in the intro.
     *
     * @param position      the postion we are in
     * @param convertView   the the layout we want to chage
     * @param parent
     * @return      return the new listView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Expense object = expenses.get(position);
        View view;

        // set adapter up
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.expense_row_layout,null);

        // link variables with widgets
        TextView tvYear = (TextView)view.findViewById(R.id.textViewYear);
        TextView tvMonth = (TextView)view.findViewById(R.id.textViewMonth);
        TextView tvType = (TextView)view.findViewById(R.id.textViewType);
        TextView tvAmount = (TextView)view.findViewById(R.id.textViewAmount);

        // set textView's text
        tvYear.setText(object.getYear());
        tvMonth.setText(object.getMonth());
        tvType.setText(object.getType());
        tvAmount.setText(String.valueOf(object.getAmount()));

        return view;
    }
}
