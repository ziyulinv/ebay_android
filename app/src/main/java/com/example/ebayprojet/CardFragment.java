package com.example.ebayprojet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardFragment extends Fragment {

    private static final String ARG_COUNT = "param1";
    private Integer counter;


    public CardFragment() {
        // Required empty public constructor
    }
    public static CardFragment newInstance(Integer counter) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(counter == 0) {
            TextView title = view.findViewById(R.id.titleDetail);
            LinearLayout product = view.findViewById(R.id.product);
            TextView priceDetail = view.findViewById(R.id.priceDetail);
            TextView shippingPriceDetail = view.findViewById(R.id.shippingPriceDetail);
            TextView brandDetail = view.findViewById(R.id.brandDetail);
            TextView subtitleDetail = view.findViewById(R.id.subtitleDetail);
            ArrayList<TextView> specdetail = new ArrayList<>();
            TextView specDetail1 = view.findViewById(R.id.specDetail1);
            TextView specDetail2 = view.findViewById(R.id.specDetail2);
            TextView specDetail3 = view.findViewById(R.id.specDetail3);
            TextView specDetail4 = view.findViewById(R.id.specDetail4);
            TextView specDetail5 = view.findViewById(R.id.specDetail5);
            specdetail.add(specDetail1);
            specdetail.add(specDetail2);
            specdetail.add(specDetail3);
            specdetail.add(specDetail4);
            specdetail.add(specDetail5);

            product.setVisibility(View.VISIBLE);
            int imgSize = DetailActivity.imgGallery.size();
            if(imgSize > 7)
                imgSize = 7;
            ImageView img1 = view.findViewById(R.id.imageGallery1);
            ImageView img2 = view.findViewById(R.id.imageGallery2);
            ImageView img3 = view.findViewById(R.id.imageGallery3);
            ImageView img4 = view.findViewById(R.id.imageGallery4);
            ImageView img5 = view.findViewById(R.id.imageGallery5);
            ImageView img6 = view.findViewById(R.id.imageGallery6);
            ImageView img7 = view.findViewById(R.id.imageGallery7);
            ArrayList<ImageView> imgViews = new ArrayList<>();
            imgViews.add(img1);
            imgViews.add(img2);
            imgViews.add(img3);
            imgViews.add(img4);
            imgViews.add(img5);
            imgViews.add(img6);
            imgViews.add(img7);
            priceDetail.setText(DetailActivity.price);
            shippingPriceDetail.setText("ships for "+DetailActivity.shippingPrice);

            if(imgSize == 0){
                img1.setImageDrawable(getActivity().getApplicationContext().getDrawable(R.drawable.ebay_default));
            }
            else{
                for(int i = 0; i < imgSize; i++) {
                    Picasso.with(getActivity().getApplicationContext()).load(DetailActivity.imgGallery.get(i)).resize(0,1000).into(imgViews.get(i));
                }
            }

            LinearLayout brandVisible = view.findViewById(R.id.brandVisible);
            LinearLayout subtitleVisible = view.findViewById(R.id.subtitleVisible);
            int featureCheck = 0;
            int count = 0;
            if(!DetailActivity.subtitle.isEmpty()){
                count += 1;
                subtitleDetail.setText(DetailActivity.subtitle);
            }
            else{subtitleVisible.setVisibility(View.GONE);};
            for(int i = 0; i < DetailActivity.specName.size(); i++){
                if(DetailActivity.specName.get(i).equals("Brand")){
                    count = 2;
                    brandDetail.setText(DetailActivity.specValue.get(i));
                }
                else{
                    String s = "\u2022 " + DetailActivity.specValue.get(i);
                    int j = 4;
                    if(featureCheck < j)
                        j = featureCheck;
                    specdetail.get(j).setText(s);
                    specdetail.get(j).setVisibility(View.VISIBLE);
                    featureCheck += 1;
                }
            }
            if(count < 2)
                brandVisible.setVisibility(View.GONE);

            if(count == 0){
                LinearLayout featureVisible = view.findViewById(R.id.featureVisible);
                featureVisible.setVisibility(View.GONE);
                TextView hr = view.findViewById(R.id.hr);
                hr.setVisibility(View.GONE);
            }
            if(featureCheck == 0){
                LinearLayout specVisible = view.findViewById(R.id.specVisible);
                specVisible.setVisibility(View.GONE);
                TextView hr2 = view.findViewById(R.id.hr2);
                hr2.setVisibility(View.GONE);
            }

            title.setText(DetailActivity.title);
        }

        else if(counter == 1){
            LinearLayout returnDetailKey1 = view.findViewById(R.id.returnDetailKey1);
            LinearLayout returnDetailKey2 = view.findViewById(R.id.returnDetailKey2);
            LinearLayout returnDetailKey3 = view.findViewById(R.id.returnDetailKey3);
            LinearLayout returnDetailKey4 = view.findViewById(R.id.returnDetailKey4);
            LinearLayout returnDetailKey5 = view.findViewById(R.id.returnDetailKey5);
            LinearLayout returnDetailKey6 = view.findViewById(R.id.returnDetailKey6);
            LinearLayout returnDetailKey7 = view.findViewById(R.id.returnDetailKey7);
            LinearLayout returnDetailKey8 = view.findViewById(R.id.returnDetailKey8);
            LinearLayout returnDetailKey9 = view.findViewById(R.id.returnDetailKey9);
            TextView returnDetailValue1 = view.findViewById(R.id.returnDetailValue1);
            TextView returnDetailValue2 = view.findViewById(R.id.returnDetailValue2);
            TextView returnDetailValue3 = view.findViewById(R.id.returnDetailValue3);
            TextView returnDetailValue4 = view.findViewById(R.id.returnDetailValue4);
            TextView returnDetailValue5 = view.findViewById(R.id.returnDetailValue5);
            TextView returnDetailValue6 = view.findViewById(R.id.returnDetailValue6);
            TextView returnDetailValue7 = view.findViewById(R.id.returnDetailValue7);
            TextView returnDetailValue8 = view.findViewById(R.id.returnDetailValue8);
            TextView returnDetailValue9 = view.findViewById(R.id.returnDetailValue9);
            LinearLayout infoDetailKey1 = view.findViewById(R.id.infoDetailKey1);
            LinearLayout infoDetailKey2 = view.findViewById(R.id.infoDetailKey2);
            LinearLayout infoDetailKey3 = view.findViewById(R.id.infoDetailKey3);
            LinearLayout infoDetailKey4 = view.findViewById(R.id.infoDetailKey4);
            LinearLayout infoDetailKey5 = view.findViewById(R.id.infoDetailKey5);
            TextView infoDetailValue1 = view.findViewById(R.id.infoDetailValue1);
            TextView infoDetailValue2 = view.findViewById(R.id.infoDetailValue2);
            TextView infoDetailValue3 = view.findViewById(R.id.infoDetailValue3);
            TextView infoDetailValue4 = view.findViewById(R.id.infoDetailValue4);
            TextView infoDetailValue5 = view.findViewById(R.id.infoDetailValue5);
            LinearLayout sellerInfo = view.findViewById(R.id.sellerInfo);
            sellerInfo.setVisibility(View.VISIBLE);

            if(DetailActivity.userId.isEmpty())
                infoDetailKey1.setVisibility(View.GONE);
            else
                infoDetailValue1.setText(DetailActivity.userId);
            if(DetailActivity.feedbackRatingStar.isEmpty())
                infoDetailKey2.setVisibility(View.GONE);
            else
                infoDetailValue2.setText(DetailActivity.feedbackRatingStar);
            if(DetailActivity.feedbackScore.isEmpty())
                infoDetailKey3.setVisibility(View.GONE);
            else
                infoDetailValue3.setText(DetailActivity.feedbackScore);
            if(DetailActivity.positiveFeedbackPercent.isEmpty())
                infoDetailKey4.setVisibility(View.GONE);
            else
                infoDetailValue4.setText(DetailActivity.positiveFeedbackPercent);
            if(DetailActivity.topRatedSeller.isEmpty())
                infoDetailKey5.setVisibility(View.GONE);
            else
                infoDetailValue5.setText(DetailActivity.topRatedSeller);

            if(DetailActivity.refund.isEmpty())
                returnDetailKey1.setVisibility(View.GONE);
            else
                returnDetailValue1.setText(DetailActivity.refund);
            if(DetailActivity.returnsWidthin.isEmpty())
                returnDetailKey2.setVisibility(View.GONE);
            else
                returnDetailValue2.setText(DetailActivity.returnsWidthin);
            if(DetailActivity.shippingCostPaidBy.isEmpty())
                returnDetailKey3.setVisibility(View.GONE);
            else
                returnDetailValue3.setText(DetailActivity.shippingCostPaidBy);
            if(DetailActivity.returnsAccepted.isEmpty())
                returnDetailKey4.setVisibility(View.GONE);
            else
                returnDetailValue4.setText(DetailActivity.returnsAccepted);
            if(DetailActivity.internationalRetrnsAccepted.isEmpty())
                returnDetailKey5.setVisibility(View.GONE);
            else
                returnDetailValue5.setText(DetailActivity.internationalRetrnsAccepted);
            if(DetailActivity.internationalRefund.isEmpty())
                returnDetailKey6.setVisibility(View.GONE);
            else
                returnDetailValue6.setText(DetailActivity.internationalRefund);
            if(DetailActivity.internationalReturnsWithin.isEmpty())
                returnDetailKey7.setVisibility(View.GONE);
            else
                returnDetailValue7.setText(DetailActivity.internationalReturnsWithin);
            if(DetailActivity.internationalShippingCostPaidBy.isEmpty())
                returnDetailKey8.setVisibility(View.GONE);
            else
                returnDetailValue8.setText(DetailActivity.internationalShippingCostPaidBy);
            if(DetailActivity.Description.isEmpty())
                returnDetailKey9.setVisibility(View.GONE);
            else
                returnDetailValue9.setText(DetailActivity.Description);
        }

        else if(counter == 2){
            LinearLayout shippingDetailKey1 = view.findViewById(R.id.shippingDetailKey1);
            LinearLayout shippingDetailKey2 = view.findViewById(R.id.shippingDetailKey2);
            LinearLayout shippingDetailKey3 = view.findViewById(R.id.shippingDetailKey3);
            LinearLayout shippingDetailKey4 = view.findViewById(R.id.shippingDetailKey4);
            LinearLayout shippingDetailKey5 = view.findViewById(R.id.shippingDetailKey5);
            LinearLayout shippingDetailKey6 = view.findViewById(R.id.shippingDetailKey6);
            TextView shippingDetailValue1 = view.findViewById(R.id.shippingDetailValue1);
            TextView shippingDetailValue2 = view.findViewById(R.id.shippingDetailValue2);
            TextView shippingDetailValue3 = view.findViewById(R.id.shippingDetailValue3);
            TextView shippingDetailValue4 = view.findViewById(R.id.shippingDetailValue4);
            TextView shippingDetailValue5 = view.findViewById(R.id.shippingDetailValue5);
            TextView shippingDetailValue6 = view.findViewById(R.id.shippingDetailValue6);

            if(DetailActivity.handlingTime.isEmpty())
                shippingDetailKey1.setVisibility(View.GONE);
            else
                shippingDetailValue1.setText(DetailActivity.handlingTime);
            if(DetailActivity.oneDayShippingAvailable.isEmpty())
                shippingDetailKey2.setVisibility(View.GONE);
            else
                shippingDetailValue2.setText(DetailActivity.oneDayShippingAvailable);
            if(DetailActivity.shippingType.isEmpty())
                shippingDetailKey3.setVisibility(View.GONE);
            else
                shippingDetailValue3.setText(DetailActivity.shippingType);
            if(DetailActivity.shippingFrom.isEmpty())
                shippingDetailKey4.setVisibility(View.GONE);
            else
                shippingDetailValue4.setText(DetailActivity.shippingFrom);
            if(DetailActivity.shipToLocation.isEmpty())
                shippingDetailKey5.setVisibility(View.GONE);
            else
                shippingDetailValue5.setText(DetailActivity.shipToLocation);
            if(DetailActivity.expeditedShipping.isEmpty())
                shippingDetailKey6.setVisibility(View.GONE);
            else
                shippingDetailValue6.setText(DetailActivity.expeditedShipping);

            LinearLayout Shipping = view.findViewById(R.id.Shipping);
            Shipping.setVisibility(View.VISIBLE);
        }

    }
}