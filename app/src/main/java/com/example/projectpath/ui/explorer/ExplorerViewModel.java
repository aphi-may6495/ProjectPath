package com.example.projectpath.ui.explorer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExplorerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExplorerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is explorer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}