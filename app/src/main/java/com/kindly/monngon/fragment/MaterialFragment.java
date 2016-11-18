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
import com.kindly.monngon.MainActivity;
import com.kindly.monngon.R;
import com.kindly.monngon.adapter.ListMaterialAdapter;
import com.kindly.monngon.model.Material;
import com.kindly.monngon.model.MaterialReponse;
import com.kindly.monngon.util.ApiClient;
import com.kindly.monngon.util.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by rau muong on 15/11/2016.
 */
public class MaterialFragment extends Fragment {
    private View mView;

    private MainActivity mainActivity;
    private RecyclerView rvSupport;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Material> supportArrayList;
    private ArrayList<Material> tmpSupportArrayList;

    private boolean isNetworkError, isNoConnection, isNoData;
    private TextView txtError;
    private ProgressBar pgLoading;

    private RequestQueue queue;
    private ListMaterialAdapter custommerAdapter;

    public static MaterialFragment newInstance() {
        MaterialFragment myFragment = new MaterialFragment();

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

        supportArrayList = new ArrayList<Material>();
        tmpSupportArrayList = new ArrayList<Material>();
        custommerAdapter = new ListMaterialAdapter(supportArrayList, mainActivity);
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
//        GsonRequest<MaterialReponse> postRequest = new GsonRequest<MaterialReponse>(
//                Request.Method.GET, UrlHelper.getProducts, MaterialReponse.class, null, null,
//                new Response.Listener<MaterialReponse>() {
//                    public void onResponse(MaterialReponse response) {
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

        Call<MaterialReponse> call = apiService.getMaterial();
        call.enqueue(new Callback<MaterialReponse>() {

            @Override
            public void onResponse(Call<MaterialReponse> call, retrofit2.Response<MaterialReponse> response) {
                processSupportResponse(response.body());
            }

            @Override
            public void onFailure(Call<MaterialReponse> call, Throwable t) {
                isNetworkError = true;
                displayCustommertFirst();
            }
        });
    }


    private void processSupportResponse(MaterialReponse messages) {
        try {
            tmpSupportArrayList.clear();
            if (messages.getSuccess() == 1) {
                if (messages.getMaterials() != null && messages.getMaterials().size() > 0) {
                    tmpSupportArrayList.addAll(messages.getMaterials());
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