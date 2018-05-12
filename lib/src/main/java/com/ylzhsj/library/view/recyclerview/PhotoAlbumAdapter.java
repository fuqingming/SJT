package com.ylzhsj.library.view.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ylzhsj.library.R;
import com.ylzhsj.library.util.imagetrans.CustomTransform;
import com.ylzhsj.library.util.imagetrans.MyImageLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import it.liuting.imagetrans.ImageTrans;
import it.liuting.imagetrans.ScaleType;
import it.liuting.imagetrans.listener.SourceImageViewGet;

public class PhotoAlbumAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

	private Context context;
	private List<String> images = new ArrayList<>();
	private RecyclerView recyclerView;
	private int itemSize;

	public PhotoAlbumAdapter(Context context,int itemSize,RecyclerView recyclerView,List<String> images) {
		this.context = context;
		this.itemSize = itemSize;
		this.recyclerView = recyclerView;
		this.images = images;
	}

	@Override
	public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		ImageView imageView = new ImageView(parent.getContext());
		imageView.setLayoutParams(new ViewGroup.LayoutParams(itemSize, itemSize));
		return new PhotoViewHolder(imageView);
	}

	@Override
	public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
		Glide.with(context).load(images.get(position))
				.placeholder(R.drawable.place_holder)
				.transform(new CustomTransform(context, ScaleType.CENTER_CROP))
				.into((ImageView) holder.itemView);
//		File file = new File(images.get(position));
//		Glide.with(context).load(file).into((ImageView) holder.itemView);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ImageTrans.with(context)
						.setImageList(images)
						.setSourceImageView(new SourceImageViewGet() {
							@Override
							public ImageView getImageView(int pos) {
								int layoutPos = recyclerView.indexOfChild(holder.itemView);
								int viewPos = layoutPos + pos - position;
								View view = recyclerView.getChildAt(viewPos);
								if (view != null) return (ImageView) view;
								return null;
							}
						})
						.setScaleType(ScaleType.CENTER_CROP)
						.setImageLoad(new MyImageLoad())
						.setNowIndex(position)
						.show();
			}
		});
	}

	@Override
	public int getItemCount() {
		return images.size();
	}

}
