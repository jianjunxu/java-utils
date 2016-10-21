package com.jxlx.jayden.guava.concurrent;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @author jianjun.xu
 * @description
 * @email jianjun.xu@vipshop.com
 * @date 16/8/17 下午11:09
 */
public class ListeningExecutorServiceOper {
	public static void main(String[] args) {
		ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
		ListenableFuture<String> submit = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return execute();
			}
		});
		Futures.addCallback(submit, new FutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				System.out.println("-----success----result:" + result);
			}

			@Override
			public void onFailure(Throwable t) {
				System.out.println("-----failure----" + t);
			}
		});
		MoreExecutors.shutdownAndAwaitTermination(executorService, 5, TimeUnit.SECONDS);
	}

	private static String execute() {
//		int i = 1 / 0;
		return "execute()";
	}
}
