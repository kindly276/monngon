package com.vccorp.monngon.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.thaond.library.util.Utils;
import com.vccorp.monngon.MainActivity;
import com.vccorp.monngon.R;
import com.vccorp.monngon.util.UrlHelper;


/**
 * Created by PC0280 on 1/25/2016.
 */
public class HomeFragment extends Fragment {
    private View mView;

    private MainActivity mainActivity;
    private RecyclerView rvSupport;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Support> supportArrayList;
    private ArrayList<Support> tmpSupportArrayList;

    private boolean isNetworkError, isNoConnection, isNoData, isUnAuthenticated, isRefresh, isOutOfData, isLoadMore;
    private TextView txtError;
    private ProgressBar pgLoading;

    private RequestQueue queue;
    private ListSupportAdapter custommerAdapter;
    private int pageId = 1;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private String role;
    private LinearLayout layout_search;

    public static HomeFragment newInstance() {
        HomeFragment myFragment = new HomeFragment();

        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mView = inflater.inflate(R.layout.fragment_list_support, container, false);


        txtError = (TextView) mView.findViewById(R.id.txtError);
        pgLoading = (ProgressBar) mView.findViewById(R.id.pgLoading);
        rvSupport = (RecyclerView) mView.findViewById(R.id.recycle_list_support);
        rvSupport.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // use a linear layout manager
        rvSupport.setLayoutManager(mLayoutManager);

        supportArrayList = new ArrayList<Support>();
        tmpSupportArrayList = new ArrayList<Support>();
        custommerAdapter = new ListSupportAdapter(supportArrayList, mainActivity);
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
                    pageId = 1;
                    showDoGetListCustommer();
                }
            }
        });

    }


    private void doGetListSupportFirst() {
        queue = Constants.getQueue(mainActivity);

        GsonRequest<ListSupport> postRequest = new GsonRequest<ListSupport>(
                Request.Method.GET, UrlHelper.GET_SUPPORT_PROJECT, ListSupport.class, null, null,
                new Response.Listener<ListSupport>() {
                    public void onResponse(ListSupport response) {
                        processSupportResponse(response);
                    }
                }, new Response.ErrorListener(

        ) {
            public void onErrorResponse(VolleyError error) {
                isNetworkError = true;
                displayCustommertFirst();
            }
        });
        queue.add(postRequest);
    }

    private void processSupportResponse(ListSupport messages) {
        try {
            tmpSupportArrayList.clear();
            if (messages.getStatus() == 1) {
                if (messages.getSupport() != null && messages.getSupport().size() > 0) {
                    tmpSupportArrayList.addAll(messages.getSupport());
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
                    } else if (isUnAuthenticated) {
                        txtError.setText(mainActivity.getResources().getString(R.string.un_authenticated));
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
                        custommerAdapter.notifyItemInserted(supportArrayList.size());
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