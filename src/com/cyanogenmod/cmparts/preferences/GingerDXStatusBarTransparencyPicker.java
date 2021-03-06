package com.cyanogenmod.cmparts.preferences;

import com.cyanogenmod.cmparts.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.preference.DialogPreference;
import android.provider.Settings;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public final class GingerDXStatusBarTransparencyPicker extends DialogPreference implements
		OnSeekBarChangeListener {
	
	private static final int MAX = 255;
	
	private int mValue;
	private SeekBar mSeekBar;
	private TextView mTextView;

	public GingerDXStatusBarTransparencyPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		mValue = Settings.System.getInt(context.getContentResolver(), Settings.System.ACHEP_STATUS_BAR_BACKGROUND_TRANSPARENCY, 255);
	}

	@Override
	protected View onCreateDialogView() {
		View view = ((LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.gingerdx_preference_transparency_picker, null);

		mSeekBar = (SeekBar) view.findViewById(R.id.seek_bar);
		mSeekBar.setMax(MAX);
		mSeekBar.setProgress(mValue);
		mSeekBar.setOnSeekBarChangeListener(this);
		
		mTextView = (TextView) view.findViewById(R.id.value);
		mTextView.setText(Integer.toString(mValue));
		
		TextView textMaxLabel = (TextView) view.findViewById(R.id.max);
		textMaxLabel.setText("255");
		TextView textMinLabel = (TextView) view.findViewById(R.id.min);
		textMinLabel.setText("0");
		return view;
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		super.onDialogClosed(positiveResult);
		if (positiveResult) {
			Settings.System.putInt(getContext().getContentResolver(), Settings.System.ACHEP_STATUS_BAR_BACKGROUND_TRANSPARENCY, mValue);			
		}
	}

	public void onProgressChanged(SeekBar seek, int value, boolean fromTouch) {
		mValue = value;
		mTextView.setText(Integer.toString(value));
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub		
	}
}