package com.naman14.timber.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.naman14.timber.R;
import com.naman14.timber.adapters.SongsListAdapterHorizontal;
import com.naman14.timber.dataloaders.SongLoader;
import com.naman14.timber.dataloaders.TopTracksLoader;
import com.naman14.timber.models.Song;

import java.util.List;

public class HomeActivity extends BaseActivity {
    RecyclerView recyclerView;
    SongsListAdapterHorizontal mAdapter;
    AppCompatActivity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recycler);
        mContext = HomeActivity.this;
        new loadRecentlyPlayed().execute("");
    }

    private class loadRecentlyPlayed extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            TopTracksLoader loader = new TopTracksLoader(mContext, TopTracksLoader.QueryType.RecentSongs);
            List<Song> recentsongs = SongLoader.getSongsForCursor(TopTracksLoader.getCursor());
            mAdapter = new SongsListAdapterHorizontal(mContext, recentsongs, true, false);
            //mAdapter.setPlaylistId(playlistID);
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            setRecyclerViewAapter();
        }

        @Override
        protected void onPreExecute() {
        }
    }

    private void setRecyclerViewAapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayout.HORIZONTAL, false));
        recyclerView.setAdapter(mAdapter);
    }
}
