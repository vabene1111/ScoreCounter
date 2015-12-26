package ua.napps.scorekeeper.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import ua.com.napps.scorekeeper.R;
import ua.napps.scorekeeper.Interactors.Dice;

import static ua.napps.scorekeeper.Interactors.Dice.getInstance;

// TODO: wrap into DialogFragment
/*
public class DatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
            .setTitle(R.string.date_picker_title)
            .setPositiveButton(android.R.string.ok, null)
            .create();
    }
}
*/
public class DiceDialog extends AlertDialog.Builder
        implements EditText.OnFocusChangeListener {
    @Bind(R.id.et1)
    EditText amount;
    @Bind(R.id.et2)
    EditText min;
    @Bind(R.id.et3)
    EditText max;
    @Bind(R.id.et4)
    EditText bonus;

    public DiceDialog(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.dice_dialog, null);
        setView(view);
        ButterKnife.bind(this, view);
        final MainActivity mainActivity = (MainActivity) context;
        final Dice dice = getInstance();
        setPositiveButton(context.getString(R.string.button_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateDice();
                mainActivity.setDiceFormula(dice.toString());
            }
        });
        setNegativeButton(context.getString(R.string.button_negative), null);
        amount.setOnFocusChangeListener(this);
        min.setOnFocusChangeListener(this);
        max.setOnFocusChangeListener(this);
        bonus.setOnFocusChangeListener(this);
        amount.setText(Integer.toString(dice.getAmount()));
        min.setText(Integer.toString(dice.getMinEdge()));
        max.setText(Integer.toString(dice.getMaxEdge()));
        bonus.setText(Integer.toString(dice.getBonus()));
        create().show();
    }

    private void updateDice() {
        getInstance().setAmount(Integer.parseInt(amount.getText().toString()));
        getInstance().setMinEdge(Integer.parseInt(min.getText().toString()));
        getInstance().setMaxEdge(Integer.parseInt(max.getText().toString()));
        getInstance().setBonus(Integer.parseInt(bonus.getText().toString()));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) return;
        EditText et = (EditText) v;
        if (et.length() == 0) et.setText("0");
        if ("0".equals(amount.getText().toString())) amount.setText("1");
        int minE = Integer.parseInt(min.getText().toString());
        int maxE = Integer.parseInt(max.getText().toString());
        if (minE == 999) min.setText("998");
        if (maxE - minE < 1) max.setText(String.format("%d", minE + 1));
    }
}