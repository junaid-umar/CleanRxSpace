package com.android.space.data.network.datasource

import com.android.space.FakeDataUtil
import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import io.reactivex.rxjava3.core.Single

class FakeBlogRemoteDataSource : BlogRemoteDataSource {

    private var fakeData = FakeDataUtil.Domain.getFakeBlogList()
    private var fakeBehaviour = Behaviour.SHOULD_RETURN_SUCCESS

    override fun searchBlogs(
        pageSize: Int,
        page: Int,
        query: String
    ): Single<Resource<List<Blog>>> {
        return when (fakeBehaviour) {
            Behaviour.SHOULD_RETURN_SUCCESS -> Single.fromCallable {
                Resource.Success(fakeData)
            }
            Behaviour.SHOULD_RETURN_ERROR -> Single.fromCallable {
                Resource.Error("Error")
            }
            Behaviour.SHOULD_RETURN_LOADING -> Single.fromCallable {
                Resource.Loading(fakeData)
            }
        }
    }

    fun fake_setBehaviour(behaviour: Behaviour) {
        fakeBehaviour = behaviour
    }

    fun fake_setData(data: List<Blog>) {
        fakeData = data
    }

    enum class Behaviour {
        SHOULD_RETURN_SUCCESS,
        SHOULD_RETURN_ERROR,
        SHOULD_RETURN_LOADING,
    }

}