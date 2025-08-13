import androidx.compose.foundation.layout.PaddingValues
import com.example.wallify.utlis.constants.TSizes

object TSpacingStyle {

    /**
     * Padding with app bar height equivalent to paddingWithAppBarHeight in Flutter
     * EdgeInsets.only(top: appBarHeight, left/right/bottom: defaultSpace)
     */
    val paddingWithAppBarHeight = PaddingValues(
        top = TSizes.appBarHeight,
        start = TSizes.defaultSpace,
        end = TSizes.defaultSpace,
        bottom = TSizes.defaultSpace
    )

    // Additional common spacing styles for Compose

    /**
     * Default padding for screens
     */
    val defaultScreenPadding = PaddingValues(TSizes.defaultSpace)

    /**
     * Padding for content sections
     */
    val contentPadding = PaddingValues(
        horizontal = TSizes.defaultSpace,
        vertical = TSizes.md
    )

    /**
     * Padding for cards and containers
     */
    val cardPadding = PaddingValues(TSizes.md)

    /**
     * Padding for list items
     */
    val listItemPadding = PaddingValues(
        horizontal = TSizes.defaultSpace,
        vertical = TSizes.sm
    )

    /**
     * Padding for dialogs
     */
    val dialogPadding = PaddingValues(TSizes.lg)

    /**
     * Padding for bottom sheets
     */
    val bottomSheetPadding = PaddingValues(
        horizontal = TSizes.defaultSpace,
        vertical = TSizes.lg
    )
}