const d = document

const $likes = d.querySelectorAll(".comment-like")
const $dislikes = d.querySelectorAll(".comment-dislike")
const $reports = d.querySelectorAll(".comment-report")

const baseLikeURL = "http://localhost:8080/comment-reaction/like/"
const baseReportURL = "http://localhost:8080/comment-reaction/report/"

$likes.forEach( like => {
    like.addEventListener("click" , async () => {
        let container = like.parentNode
        let commentId = container.getAttribute("data-commentid")
        let likeURL = `${baseLikeURL}${commentId}?is_positive=true`
        console.log(likeURL)
        commentReaction(likeURL)
    })
})


$dislikes.forEach( dislike => {
    dislike.addEventListener("click" , async () => {
        let container = dislike.parentNode
        let commentId = container.getAttribute("data-commentid")
        let dislikeURL = `${baseLikeURL}${commentId}?is_positive=false`
        console.log(dislikeURL)
        commentReaction(dislikeURL)
    })
})


$reports.forEach( report => {
    report.addEventListener("click" , async () => {
        let container = report.parentNode
        let commentId = container.getAttribute("data-commentid")
        let reportURL = `${baseReportURL}${commentId}`
        console.log(reportURL)
})
})

async function commentReaction(url) {
    try {
        let res = await fetch(url)
        let json = await res.json()
        console.log(json)
    } catch (error) {
        alert("error: " + error)
    }
}