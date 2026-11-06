package viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel actual constructor() {
	protected actual val coroutineScope: CoroutineScope = MainScope()

	actual open fun clear() {
		coroutineScope.cancel()
	}
}
