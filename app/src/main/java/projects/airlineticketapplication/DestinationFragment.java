package projects.airlineticketapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DestinationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestinationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DestinationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DestinationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DestinationFragment newInstance(String param1, String param2) {
        DestinationFragment fragment = new DestinationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private List<DestinationItem> destinationItem;
    AutoCompleteTextView  editTextTo, editTextFrom;
    EditText editTextDeparture, editTextReturn;
    TextView textViewPassenger;
    Button buttonSearch;
    RadioGroup group;
    RadioButton button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destination, container, false);

        group = view.findViewById(R.id.radioGroup);
        editTextReturn = view.findViewById(R.id.editTextDateReturn);
        editTextDeparture = view.findViewById(R.id.editTextDateDeparture);
        textViewPassenger = view.findViewById(R.id.textViewPassenger);
        buttonSearch = view.findViewById(R.id.button);
        int radioId = group.getCheckedRadioButtonId();
        button = view.findViewById(radioId);

        destination(view);
        date();

        editTextFrom.setOnTouchListener((view1, motionEvent) -> {
            visibility();
            return false;
        });

        editTextTo.setOnTouchListener((view2, motionEvent) -> {
            visibility();
            return false;
        });
        RadioButton button1 = view.findViewById(R.id.radioTripOneWay);
        button1.setOnClickListener(view3 -> {
            editTextReturn.setVisibility(View.INVISIBLE);
        });

        RadioButton button2 = view.findViewById(R.id.radioTripRoundTrip);
        button2.setOnClickListener(view4 -> {
            editTextReturn.setVisibility(View.VISIBLE);
        });

        RadioButton button3 = view.findViewById(R.id.radioTripAnotherAirport);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("three", button3.toString());
            }
        });

        return view;
    }

    private void visibility() {
        group.setVisibility(View.VISIBLE);
        editTextReturn.setVisibility(View.VISIBLE);
        editTextDeparture.setVisibility(View.VISIBLE);
        textViewPassenger.setVisibility(View.VISIBLE);
        buttonSearch.setVisibility(View.VISIBLE);
    }

    private void destination(View view) {
        editTextFrom = view.findViewById(R.id.editFromDestination);
        editTextTo = view.findViewById(R.id.editToDestination);

        fillDestinationList();

        DestinationAdapter adapter = new DestinationAdapter(Objects.requireNonNull(getActivity()), destinationItem);
        editTextFrom.setAdapter(adapter);
        editTextTo.setAdapter(adapter);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void date() {
        Log.d("text", "text");
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        editTextDeparture.setOnTouchListener((view, motionEvent) -> {
            DatePickerDialog.OnDateSetListener mDateSetListener = (view1, year12, month12, day12) -> {
                month12 = month12 +1;
                String date = day12 +"/"+ month12 +"/"+ year12;
                editTextDeparture.setText(date);
            };

            DatePickerDialog d = new DatePickerDialog(getActivity(),
                    R.style.Theme_AirlineTicketApplication, mDateSetListener, year, month, day);
            d.show();
            return false;
        });

        editTextReturn.setOnTouchListener((view, motionEvent) -> {
            Log.d("edit", "edit");
            DatePickerDialog.OnDateSetListener mDateSetListener = (view1, year1, month1, day1) -> {
                month1 = month1 +1;
                String date = day1 +"/"+ month1 +"/"+ year1;
                Log.d("text", date);
                editTextReturn.setText(date);
            };

            DatePickerDialog d = new DatePickerDialog(getActivity(),
                    R.style.Theme_AirlineTicketApplication, mDateSetListener, year, month, day);
            d.show();
            return false;
        });
    }

    private void fillDestinationList() {
        destinationItem = new ArrayList<>();
        destinationItem.add(new DestinationItem("Stockholm - Arlanda", "Sweden", "ARN"));
        destinationItem.add(new DestinationItem("Stockholm - Bromma", "Sweden", "BMA"));
        destinationItem.add(new DestinationItem("Boston, MA - Logan Int airport", "USA", "BOS"));
        destinationItem.add(new DestinationItem("Copenhagen", "Denmark", "CHP"));
        destinationItem.add(new DestinationItem("Oslo - Gardermoen", "Norway", "OSL"));
        destinationItem.add(new DestinationItem("Stuttgart - Manfred Rommel", "Germany", "STR"));
        destinationItem.add(new DestinationItem("Istanbul - Ataturk", "Turkey", "IST"));
        destinationItem.add(new DestinationItem("Austin, TX - Bergstrom", "USA", "AUS"));
    }
}