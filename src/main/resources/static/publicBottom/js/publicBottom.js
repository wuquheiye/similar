$(function() {
	// 公共底部点击变色和显示隐藏
	$(".publicBottom").on("click",".publicBottomColXs5",function() {
		if ($(this).children(".publicBottomImages").attr("src").indexOf("Black") != -1) {
			// 显示隐藏start
			$(".centerContain .center").addClass("hidden");
			$(".centerContain ."+ $(this).children(".publicBottomImages").attr("id")).removeClass("hidden");
			// 显示隐藏end
			// 底部点击更换图片start
			$(".publicBottom img").each(function() {
				this.src = this.src.replace("Red", "Black");
			});
			$(this).children(".publicBottomImages").attr("src",$(this).children(".publicBottomImages").attr("src").replace("Black", "Red"));
					// 底部点击更换图片end
		}
	});
})