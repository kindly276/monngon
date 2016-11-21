package com.vccorp.monngon.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.kindly.monngon.R;
import com.kindly.monngon.adapter.ListMonAdapter;
import com.kindly.monngon.model.HomeReponse;
import com.kindly.monngon.model.MaterialReponse;
import com.kindly.monngon.model.Mon;
import com.kindly.monngon.util.ApiClient;
import com.kindly.monngon.util.ApiInterface;
import com.kindly.monngon.util.Constants;
import com.kindly.monngon.util.UrlHelper;
import com.thaond.library.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static com.thaond.library.util.Utils.queue;

/**
 * Created by PC0353 on 11/17/2016.
 */

public class ListMonFragment extends AppCompatActivity {

    private RecyclerView rvMon;
    private LinearLayoutManager mLayoutManager;
    private List<Mon> listMon;
    private List<Mon> tmpListMon;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isNetworkError, isNoConnection, isNoData, isUnAuthenticated, isRefresh, isOutOfData, isLoadMore;
    private TextView txtError;
    private ProgressBar pgLoading;

    private ListMonAdapter custommerAdapter;
    private int pageId = 1;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
//    public static ListCustommerFragment newInstance(String sentDate, String name, int idStatus, int isExpried) {
//        ListCustommerFragment myFragment = new ListCustommerFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.SENT_DATE, sentDate);
//        bundle.putString(Constants.NAME, name);
//        bundle.putInt(Constants.STATUS, idStatus);
//        bundle.putInt(Constants.IS_EXPIRED, isExpried);
//        myFragment.setArguments(bundle);
//        return myFragment;
//    }

//    public static ListCustommerFragment newInstance() {
//        ListCustommerFragment myFragment = new ListCustommerFragment();
//        return myFragment;
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark,
                R.color.colorPrimaryDark);
        txtError = (TextView) findViewById(R.id.txtError);
        pgLoading = (ProgressBar)findViewById(R.id.pgLoading);
        rvMon = (RecyclerView) findViewById(R.id.rv_mon);
        rvMon.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // use a linear layout manager
        rvMon.setLayoutManager(mLayoutManager);

        listMon = new ArrayList<Mon>();
        tmpListMon = new ArrayList<Mon>();

        custommerAdapter =new ListMonAdapter( listMon,ListMonFragment.this);
        rvMon.setAdapter(custommerAdapter);
        initOnClickListener();
        showDoGetListCustommer();
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
        mSwipeRefreshLayout
                .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        isRefresh = true;
                        isOutOfData = false;
                        pageId = 1;
                        showDoGetListCustommer();
                    }
                });
        rvMon
                .setOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView,
                                           int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        mSwipeRefreshLayout.setEnabled(mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();
                        pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                        if (loading) {
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                loading = false;
                                if (!isLoadMore) {
                                    if (tmpListMon.size() == Constants.NUMBER_PAGE && !isOutOfData) {
                                        listMon.add(null);
                                        custommerAdapter.notifyItemInserted(listMon.size() - 1);
                                        pageId++;
                                        doGetListMoreCustommer();
                                    }
                                }

                            }
                        }
                    }
                });
    }


    private void doGetListCustommerFirst() {
//        final Map<String, String> params = new HashMap<String, String>();
//        try {
//            SharedPreferences sharedPref = mContext
//                    .getSharedPreferences(
//                            getResources().getString(
//                                    R.string.login_share_private),
//                            Context.MODE_PRIVATE);
//            String access_key = sharedPref.getString(Constants.ACCESS_KEY, "");
//            params.put(Constants.ACCESS_KEY, access_key);
//            params.put(Constants.PAGE_ID, "" + pageId);
//            params.put(Constants.NAME, getArguments().getString(Constants.NAME));
//            params.put(Constants.SENT_DATE, getArguments().getString(Constants.SENT_DATE));
//            params.put(Constants.IS_EXPIRED, Integer.toString(getArguments().getInt(Constants.IS_EXPIRED)));
//            params.put(Constants.STATUS, Integer.toString(getArguments().getInt(Constants.STATUS)));
//            Log.e("thaond", "Access_key List" +access_key);
//        } catch (Exception e) {
//
//        }
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<HomeReponse> call = apiService.getMons(pageId,Constants.NUMBER_PAGE);
        call.enqueue(new Callback<HomeReponse>() {

            @Override
            public void onResponse(Call<HomeReponse> call, retrofit2.Response<HomeReponse> response) {
                processCustommerResponse(response.body());
            }

            @Override
            public void onFailure(Call<HomeReponse> call, Throwable t) {
                isNetworkError = true;
                displayCustommertFirst();
            }
        });
    }

    private void processCustommerResponse(HomeReponse messages) {
        try {
            tmpListMon.clear();
            if (messages.getSuccess() == Constants.SUCCESS) {
                if (messages.getMons() != null && messages.getMons().size() > 0) {
                    tmpListMon.addAll(messages.getMons());
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
        if (isRefresh) {
            processRefreshData();
        } else {
            processDisplayDataFirst();
        }
    }

    private void processDisplayDataFirst() {
        try {
                if (isNetworkError) {
                    if (isNoConnection) {
                        txtError.setText(getResources().getString(R.string.no_connection));
                    } else if (isNoData) {
                        txtError.setText(getResources().getString(R.string.no_data));
                    }else {
                        txtError.setText(getResources().getString(R.string.error));
                    }
                    txtError.setVisibility(View.VISIBLE);
                    pgLoading.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setVisibility(View.GONE);

                } else {
                    if (tmpListMon.size() > 0) {
                        listMon.clear();
                        listMon.addAll(tmpListMon);
                        custommerAdapter.notifyItemInserted(listMon.size());
                        pgLoading.setVisibility(View.GONE);
                        txtError.setVisibility(View.GONE);
                        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                    } else {
                        txtError.setText(getResources().getString(R.string.no_data));
                        txtError.setVisibility(View.VISIBLE);
                        pgLoading.setVisibility(View.GONE);
                        mSwipeRefreshLayout.setVisibility(View.GONE);
                    }
                }
        } catch (Exception ex) {

        }
    }

    private void showDoGetListCustommer() {
        if (Utils.hasConnection(ListMonFragment.this)) {
            doGetListCustommerFirst();
        } else {
            isNetworkError = true;
            isNoConnection = true;
            displayCustommertFirst();
        }
    }


    private void doGetListMoreCustommer() {
        isLoadMore = true;
        isNetworkError = false;
        isNoData = false;
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<HomeReponse> call = apiService.getMons(pageId,Constants.NUMBER_PAGE);
        call.enqueue(new Callback<HomeReponse>() {

            @Override
            public void onResponse(Call<HomeReponse> call, retrofit2.Response<HomeReponse> response) {
                processMoreCutommerResponse(response.body());
            }

            @Override
            public void onFailure(Call<HomeReponse> call, Throwable t) {
                isNetworkError = true;
                displayCustommerMore();
            }
        });
    }

    private void processMoreCutommerResponse(HomeReponse messages) {
        try {
            tmpListMon.clear();
            if (messages.getSuccess() == Constants.SUCCESS) {
                if (!messages.getMons().equals("null") && messages.getMons() != null && messages.getMons().size() > 0) {
                    tmpListMon.addAll(messages.getMons());
                    isNetworkError = false;
                } else {
                    isNoData = true;
                    isNetworkError = true;
                }
            }else {
                isNetworkError = true;
            }
        } catch (Exception e) {
            isNetworkError = true;
        } finally {
            displayCustommerMore();
        }


    }

    private void displayCustommerMore() {

        loading = true;
        Utils.logE("thaond","Loading"+ loading);
        Utils.logE("thaond","tmpListMon"+ tmpListMon.size());
        try {
                if (!isNetworkError) {
                    if (tmpListMon.size() > 0) {
                        //   remove progress item
                        listMon.remove(listMon.size() - 1);
                        custommerAdapter.notifyItemRemoved(listMon.size());
                        //add items one by one
                        listMon.addAll(tmpListMon);
                        custommerAdapter.notifyItemInserted(listMon.size());
                    }
                } else {
                    if (isNoData) {
                        Utils.logE("thaond", "Load more thanh cong");
                        isOutOfData = true;
                        listMon.remove(listMon.size() - 1);
                        custommerAdapter.notifyItemRemoved(listMon.size());
                    }  else {
                        Utils.logE("thaond", "Load more that bai");
//                        showSnackbarErrorLoadMore();
                        listMon.remove(listMon.size() - 1);
                        custommerAdapter.notifyItemRemoved(listMon.size());
                        Project project = new Project();
                        project.setTypeBottom(ListProjectAdapter.VIEW_BOTTOM);
                        listProject.add(project);
                        adapter.notifyItemInserted(listProject.size());
                    }
                }
                isLoadMore = false;
        } catch (Exception e) {

        }
    }

    private void processRefreshData() {
        try {
            if (mContext != null) {
                if (isNetworkError) {
                    if (isNoConnection) {
                        txtError.setText(mContext.getResources().getString(R.string.no_connection));
                    } else if (isNoData) {
                        txtError.setText(mContext.getResources().getString(R.string.no_data));
                    } else if (isUnAuthenticated) {
                        txtError.setText(mContext.getResources().getString(R.string.un_authenticated));
                    } else {
                        txtError.setText(mContext.getResources().getString(R.string.error));
                    }
                    txtError.setVisibility(View.VISIBLE);
                    pgLoading.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setVisibility(View.GONE);

                } else {
                    if (tmpListMon.size() > 0) {
                        listMon.clear();
                        listMon.addAll(tmpListMon);
                        custommerAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                    } else {
                        txtError.setText(mContext.getResources().getString(R.string.no_data));
                        txtError.setVisibility(View.VISIBLE);
                        mSwipeRefreshLayout.setVisibility(View.GONE);
                    }
                }
            }

        } catch (Exception ex) {
            if (mContext != null) {
                Utils.showMessage(mContext, mContext.getResources().getString(R.string.error));
            }
        } finally {
            mSwipeRefreshLayout.setRefreshing(false);
            isRefresh = false;
        }
    }

    private void showSnackbarErrorLoadMore() {

        Snackbar snackbar = Snackbar
                .make(layout_search, mContext.getResources().getString(R.string.loadmore_error), Snackbar.LENGTH_INDEFINITE)
                .setAction(mContext.getResources().getString(R.string.btn_retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listMon.add(null);
                        custommerAdapter.notifyItemInserted(listMon.size() - 1);
                        doGetListMoreCustommer();
                    }
                });
        snackbar.setActionTextColor(Color.YELLOW);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
    private void showSnackbarTimoutLoadMore() {

        Snackbar snackbar = Snackbar
                .make(layout_search, mContext.getResources().getString(R.string.un_authenticated_snackbar), Snackbar.LENGTH_INDEFINITE)
                .setAction(mContext.getResources().getString(R.string.login_btn_login), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        ActivityOptionsCompat options =
                                Utils.makeOptionsCompat(
                                        (Activity) mContext
                                );
                        ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());
                        ((Activity) mContext).overridePendingTransition(R.anim.slide_up, R.anim.scale_down);
                        ((Activity) mContext).finish();
                    }
                });
        snackbar.setActionTextColor(Color.YELLOW);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

}