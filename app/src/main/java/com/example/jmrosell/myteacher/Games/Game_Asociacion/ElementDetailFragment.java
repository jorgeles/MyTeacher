package com.example.jmrosell.myteacher.Games.Game_Asociacion;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jmrosell.myteacher.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ElementDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ElementDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ElementDetailFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int index;
    public static final String ARG_ITEM_ID = "item_id";

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ElementDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ElementDetailFragment newInstance(String param1, String param2) {
        ElementDetailFragment fragment = new ElementDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ElementDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //   mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            index = Integer.valueOf(getArguments().getString(ARG_ITEM_ID));
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_element_detail, container, false);
        Button asociacion = (Button) rootView.findViewById(R.id.boton_atras);
        asociacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle arguments = new Bundle();
                arguments.putString(AsociacionDetailFragment.ARG_ITEM_ID, String.valueOf(index));
                AsociacionDetailFragment fragment = new AsociacionDetailFragment();
                fragment.setArguments(arguments);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.game_detail_container, fragment)
                        .commit();
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
