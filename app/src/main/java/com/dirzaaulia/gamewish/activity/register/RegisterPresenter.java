package com.dirzaaulia.gamewish.activity.register;

import com.dirzaaulia.gamewish.api.ApiClient;
import com.dirzaaulia.gamewish.api.ApiInterface;
import com.dirzaaulia.gamewish.model.Wishlist;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private RegisterView view;

    RegisterPresenter(RegisterView view) {
        this.view = view;
    }

    void registerKode(final String kode) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Wishlist> call = apiInterface.registerKode(kode);
        call.enqueue(new Callback<Wishlist>() {
            @Override
            public void onResponse(@NonNull Call<Wishlist> call, @NonNull Response<Wishlist> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Wishlist> call, @NonNull Throwable t) {
                view.onRequestError("Terjadi kesalahan saat menambahkan wishlist");
            }
        });
    }
}
