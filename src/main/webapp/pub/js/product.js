const productImages = document.querySelectorAll(".product-images img");
const productIframes = document.querySelectorAll(".product-images iframe");
const productImageSlider = document.querySelector(".image-slider");

let activeImageSlide=0;

productImages.forEach((item,i)=>{
    item.addEventListener('click',()=>{
        productImages[activeImageSlide].classList.remove('active');

        item.classList.add('active');
        productImageSlider.style.backgroundImage = `url('${item.src}')`;
        activeImageSlide = i;
    })
})

let activeIframeSlide=0;

productIframes.forEach((item,i)=>{
    item.addEventListener('hover',()=>{

        productIframes[activeIframeSlide].classList.remove('active');
        item.classList.add('active');
        productImageSlider.style.backgroundImage = `url('${item.src}')`;
        activeImageSlide = i;
    })
})

