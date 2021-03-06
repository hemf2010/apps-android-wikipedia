package org.wikipedia.descriptions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.wikipedia.R;
import org.wikipedia.json.GsonMarshaller;
import org.wikipedia.json.GsonUnmarshaller;
import org.wikipedia.page.PageTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DescriptionEditFragment extends Fragment {
    private static final String EXTRA_TITLE = "title";

    @BindView(R.id.description_edit_view) DescriptionEditView editView;
    private Unbinder unbinder;
    private PageTitle pageTitle;
    private EditViewCallback callback = new EditViewCallback();

    @NonNull
    public static DescriptionEditFragment newInstance(@NonNull PageTitle title) {
        DescriptionEditFragment instance = new DescriptionEditFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TITLE, GsonMarshaller.marshal(title));
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_description_edit, container, false);
        unbinder = ButterKnife.bind(this, view);

        pageTitle = GsonUnmarshaller.unmarshal(PageTitle.class, getActivity().getIntent().getStringExtra(EXTRA_TITLE));
        editView.setPageTitle(pageTitle);
        editView.setCallback(callback);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override public void onDestroyView() {
        unbinder.unbind();
        unbinder = null;
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_description_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_description_edit_help:
                // TODO: show tutorial!
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class EditViewCallback implements DescriptionEditView.Callback {
        @Override
        public void onSaveClick() {
            // TODO: save it!
            editView.setSaveState(true);
        }
    }
}
