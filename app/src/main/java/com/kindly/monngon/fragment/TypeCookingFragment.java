package com.kindly.monngon.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.thaond.library.util.Utils;
import com.kindly.monngon.activity.MainActivity;
import com.kindly.monngon.R;
import com.kindly.monngon.adapter.ListCookingTypeAdapter;
import com.kindly.monngon.model.CookingType;
import com.kindly.monngon.model.TypeCookingReponse;
import com.kindly.monngon.util.ApiClient;
import com.kindly.monngon.util.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by rau muong on 15/11/2016.
 */
public class TypeCookingFragment extends Fragment {
    private View mView;

    private MainActivity mainActivity;
    private RecyclerView rvSupport;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<CookingType> supportArrayList;
    private ArrayList<CookingType> tmpSupportArrayList;

    private boolean isNetworkError, isNoConnection, isNoData;
    private TextView txtError;
    private ProgressBar pgLoading;

    private RequestQueue queue;
    private ListCookingTypeAdapter custommerAdapter;

    public static TypeCookingFragment newInstance() {
        TypeCookingFragment myFragment = new TypeCookingFragment();

        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mView = inflater.inflate(R.layout.fragment_home, container, false);


        txtError = (TextView) mView.findViewById(R.id.txtError);
        pgLoading = (ProgressBar) mView.findViewById(R.id.pgLoading);
        rvSupport = (RecyclerView) mView.findViewById(R.id.rv_home);
        rvSupport.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // use a linear layout manager
        rvSupport.setLayoutManager(mLayoutManager);

        supportArrayList = new ArrayList<CookingType>();
        tmpSupportArrayList = new ArrayList<CookingType>();
        custommerAdapter = new ListCookingTypeAdapter(supportArrayList, mainActivity);
        rvSupport.setAdapter(custommerAdapter);
        initOnClickListener();
        showDoGetListCustommer();
        return mView;
    }

    private void initOnClickListener() {

        txtError.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isNoData) {
                    isNetworkError = false;
                    isNoConnection = false;
                    txtError.setVisibility(View.GONE);
                    pgLoading.setVisibility(View.VISIBLE);
                    showDoGetListCustommer();
                }
            }
        });

    }


    private void doGetListSupportFirst() {
//        queue = Utils.getQueue(mainActivity);
//
//        GsonRequest<TypeCookingReponse> postRequest = new GsonRequest<TypeCookingReponse>(
//                Request.Method.GET, UrlHelper.getProducts, TypeCookingReponse.class, null, null,
//                new Response.Listener<TypeCookingReponse>() {
//                    public void onResponse(TypeCookingReponse response) {
//                        processSupportResponse(response);
//                    }
//                }, new Response.ErrorListener(
//
//        ) {
//            public void onErrorResponse(VolleyError error) {
//                isNetworkError = true;
//                displayCustommertFirst();
//            }
//        });
//        queue.add(postRequest);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<TypeCookingReponse> call = apiService.getTypeCooking();
        call.enqueue(new Callback<TypeCookingReponse>() {

            @Override
            public void onResponse(Call<TypeCookingReponse> call, retrofit2.Response<TypeCookingReponse> response) {
                Utils.logE("thaond","abc"+response.body().toString());

                processSupportResponse(response.body());
            }

            @Override
            public void onFailure(Call<TypeCookingReponse> call, Throwable t) {
                isNetworkError = true;
                displayCustommertFirst();
            }
        });
    }


    private void processSupportResponse(TypeCookingReponse messages) {
        try {
            tmpSupportArrayList.clear();
            if (messages.getSuccess() == 1) {
                if (messages.getCachnaus() != null && messages.getCachnaus().size() > 0) {
                    tmpSupportArrayList.addAll(messages.getCachnaus());
                    isNetworkError = false;
                } else {
                    isNoData = true;
                    isNetworkError = true;
                }
            } else {
                isNetworkError = true;
            }

        } catch (Exception e) {
            isNetworkError = true;
        } finally {
            displayCustommertFirst();
        }

    }

    private void displayCustommertFirst() {

        processDisplayDataFirst();
    }

    private void processDisplayDataFirst() {
        try {
            if (mainActivity != null) {
                if (isNetworkError) {
                    if (isNoConnection) {
                        txtError.setText(mainActivity.getResources().getString(R.string.no_connection));
                    } else if (isNoData) {
                        txtError.setText(mainActivity.getResources().getString(R.string.no_data));
                    } else {
                        txtError.setText(mainActivity.getResources().getString(R.string.error));
                    }
                    txtError.setVisibility(View.VISIBLE);
                    pgLoading.setVisibility(View.GONE);
                    rvSupport.setVisibility(View.GONE);

                } else {
                    if (tmpSupportArrayList.size() > 0) {
                        supportArrayList.clear();
                        supportArrayList.addAll(tmpSupportArrayList);
                        custommerAdapter.notifyDataSetChanged();
                        pgLoading.setVisibility(View.GONE);
                        txtError.setVisibility(View.GONE);
                        rvSupport.setVisibility(View.VISIBLE);
                    } else {
                        txtError.setText(mainActivity.getResources().getString(R.string.no_data));
                        txtError.setVisibility(View.VISIBLE);
                        pgLoading.setVisibility(View.GONE);
                        rvSupport.setVisibility(View.GONE);
                    }
                }
            }
        } catch (Exception ex) {
            if (mainActivity != null) {
            }
        }
    }

    private void showDoGetListCustommer() {
        if (Utils.hasConnection(mainActivity)) {
            doGetListSupportFirst();
        } else {
            isNetworkError = true;
            isNoConnection = true;
            displayCustommertFirst();
        }
    }
}