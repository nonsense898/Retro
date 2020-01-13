package com.nonsense.retro;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Random;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UsersFragment extends Fragment {
    private static final String TAG = "SpeedDial";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    GitHubListAdapter gitHubListAdapter = new GitHubListAdapter();
    private View view;

    public UsersFragment() {
    }

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.switch_menu:
                switchIcon(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_users, container, false);
        setHasOptionsMenu(true);
        fetchUsers();
        return view;
    }

    private void fetchUsers() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        GitApi client = retrofit.create(GitApi.class);

        final int min = 120;
        final int max = 240;
        final int random = new Random().nextInt((max - min) + 1) + min;

        Call<List<GitModel>> call = client.reposForuser(random);
        call.enqueue(new Callback<List<GitModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<GitModel>> call, @NotNull Response<List<GitModel>> response) {


                List<GitModel> results = response.body();
                recyclerView = view.findViewById(R.id.recycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                gitHubListAdapter.setData(results);
                recyclerView.setAdapter(gitHubListAdapter);
                recyclerView.setHasFixedSize(true);

            }

            @Override
            public void onFailure(@NotNull Call<List<GitModel>> call, @NotNull Throwable t) {
                Log.d("retro", "" + t.getMessage());
            }
        });
    }


    public void switchIcon(MenuItem item) {
        layoutManager = recyclerView.getLayoutManager();
        if (gitHubListAdapter.isSwitchView) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            gitHubListAdapter.isSwitchView = true;
            item.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_span_1, null));

        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            gitHubListAdapter.isSwitchView = false;
            item.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_span_3, null));

        }
        gitHubListAdapter.notifyDataSetChanged();

    }
}