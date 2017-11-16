package org.grunkspin.emptystatespinner

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.*

class EmptyStateSpinner @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defaultStyleAttr: Int = android.R.attr.spinnerStyle,
        defaultStyleRes: Int = 0
) : Spinner(context, attributeSet, defaultStyleAttr, defaultStyleRes) {

    private var itemHasBeenClicked = false

    fun <T> setAdapter(adapter: ArrayAdapter<T>, emptyItem: T) {
        this.adapter = EmptyItemAdapter(adapter, emptyItem)
    }

    override fun setSelection(position: Int) {
        (adapter as? EmptyItemAdapter<*>)?.notifyItemHasBeenClicked()
        super.setSelection(position)
    }

    override fun setSelection(position: Int, animate: Boolean) {
        (adapter as? EmptyItemAdapter<*>)?.notifyItemHasBeenClicked()
        super.setSelection(position, animate)
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState()).apply {
            this.itemHasBeenClicked = this@EmptyStateSpinner.itemHasBeenClicked
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            itemHasBeenClicked = state.itemHasBeenClicked
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    private inner class EmptyItemAdapter<out T>(
            private val adapter: ArrayAdapter<T>,
            emptyItem: T
    ) : SpinnerAdapter by adapter {

        init {
            @Suppress("LeakingThis")
            adapter.insert(emptyItem, 0)
            adapter.notifyDataSetChanged()
        }

        fun notifyItemHasBeenClicked() {
            itemHasBeenClicked = true
            adapter.notifyDataSetChanged()
        }

        override fun getCount(): Int = adapter.count - 1

        override fun getItem(position: Int): Any = adapter.getItem(getAdjustedPosition(position)) as Any

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
                adapter.getView(getAdjustedPosition(position), convertView, parent)

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View =
                adapter.getDropDownView(position + 1, convertView, parent)

        private fun getAdjustedPosition(position: Int) =
                if (itemHasBeenClicked) {
                    position + 1
                } else {
                    position
                }
    }

    class SavedState : BaseSavedState {

        var itemHasBeenClicked: Boolean = false

        constructor(superState: Parcelable) : super(superState)

        private constructor(input: Parcel) : super(input) {
            itemHasBeenClicked = input.readByte() != 0.toByte()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            super.writeToParcel(parcel, flags)
            parcel.writeByte(if (itemHasBeenClicked) 1 else 0)
        }

        override fun describeContents(): Int = 0

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(parcel: Parcel): SavedState = SavedState(parcel)

            override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)
        }
    }
}

