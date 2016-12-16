package com.kindly.monngon.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.thaond.library.util.Utils;
import com.kindly.monngon.R;
import com.kindly.monngon.model.Mon;
import com.kindly.monngon.util.Constants;

/**
 * Created by PC0353 on 11/17/2016.
 */

public class DetailMonActivity extends AppCompatActivity {
    private ImageView imageMon;
    private TextView txtDescription, txtMaterial, txtCachnau;
    private AdView avBanner;
    private AdRequest adRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_mon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        imageMon = (ImageView) findViewById(R.id.image_mon);
        txtDescription = (TextView)findViewById(R.id.txt_intro);
        txtMaterial = (TextView) findViewById(R.id.txt_material);
        txtCachnau = (TextView)findViewById(R.id.txt_make_cooking);
        txtDescription = (TextView)findViewById(R.id.txt_intro);
        Mon mon = getIntent().getParcelableExtra(Constants.DATA);
        txtDescription.setText(mon.getDescription());
        txtMaterial.setText(mon.getMaterial());
        txtCachnau.setText(mon.getMaking());
        Utils.loadImage(DetailMonActivity.this, imageMon, mon.getImage());


        avBanner =(AdView)findViewById(R.id.av_banner);

        adRequest = new AdRequest.Builder().build();
        //load ads
        avBanner.loadAd(adRequest);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
