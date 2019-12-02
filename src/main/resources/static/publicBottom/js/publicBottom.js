$(function() {
	var str = window.location.href;
	var publicBottomImages = str.substr(str.indexOf("=")+1);
	if(publicBottomImages == "publicBottomInformation"){
		$(".centerContain .center").addClass("hidden");
		$(".centerContain ."+ publicBottomImages).removeClass("hidden");
		$(".publicBottom img").each(function() {
			this.src = this.src.replace("Red", "Black");
		});
		$("#publicBottomInformation").attr("src",$("#publicBottomInformation").attr("src").replace("Black", "Red"));
	}else if(publicBottomImages == "publicBottomApply"){
		$(".centerContain .center").addClass("hidden");
		$(".centerContain ."+ publicBottomImages).removeClass("hidden");
		$(".publicBottom img").each(function() {
			this.src = this.src.replace("Red", "Black");
		});
		$("#publicBottomApply").attr("src",$("#publicBottomApply").attr("src").replace("Black", "Red"));
	}else if(publicBottomImages == "publicBottomOrganization"){
		$(".centerContain .center").addClass("hidden");
		$(".centerContain ."+ publicBottomImages).removeClass("hidden");
		$(".publicBottom img").each(function() {
			this.src = this.src.replace("Red", "Black");
		});
		$("#publicBottomOrganization").attr("src",$("#publicBottomOrganization").attr("src").replace("Black", "Red"));
	}else if(publicBottomImages == "publicBottomMy"){
		$(".centerContain .center").addClass("hidden");
		$(".centerContain ."+ publicBottomImages).removeClass("hidden");
		$(".publicBottom img").each(function() {
			this.src = this.src.replace("Red", "Black");
		});
		$("#publicBottomMy").attr("src",$("#publicBottomMy").attr("src").replace("Black", "Red"));
	}

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