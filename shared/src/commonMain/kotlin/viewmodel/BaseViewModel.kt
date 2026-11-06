package viewmodel

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel() {
	protected val coroutineScope: CoroutineScope
	open fun clear()
}
