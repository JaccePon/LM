package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JaccePon123
 * @since 2018-08-24
 */
@TableName("blog_article")
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 文章主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 游客的ip
     */
    @TableField("author_ip")
    private String authorIp;
    /**
     * 作者名称
     */
    private String author;
    /**
     * 栏目id，0为其他
     */
    @TableField("column_id")
    private Integer columnId;
    /**
     * 是否推送至首页轮播  0 暂无推送 ，1首页
     */
    @TableField("index_flag")
    private Integer indexFlag;
    /**
     * 热门推荐，0不推荐，1推荐
     */
    @TableField("hot_flag")
    private Integer hotFlag;
    /**
     * 热门排行  0 不参与  1参与排行
     */
    @TableField("hot_rank")
    private Integer hotRank;
    /**
     * 是否原创 0  非原创、1原创
     */
    @TableField("original_flag")
    private Integer originalFlag;
    /**
     * 1 待审核、2 审核通过、3 草稿库、4 回收站
     */
    private Integer status;
    /**
     * 是否公开  0 公开， 1 不公开
     */
    @TableField("publicity_flag")
    private Integer publicityFlag;
    /**
     * 来源
     */
    private String source;
    /**
     * 摘要
     */
    private String abstracts;
    /**
     * 长标题
     */
    private String title;
    /**
     * 短标题
     */
    @TableField("short_title")
    private String shortTitle;
    /**
     * 内容
     */
    private String content;
    /**
     * 封面图片
     */
    @TableField("pic_path")
    private String picPath;
    /**
     * 虚拟阅读数
     */
    @TableField("virtualread_num")
    private Long virtualreadNum;
    /**
     * 真实阅读数
     */
    @TableField("realread_num")
    private Long realreadNum;
    /**
     * 点赞数
     */
    @TableField("like_num")
    private Long likeNum;
    /**
     * 分享数
     */
    @TableField("share_num")
    private Long shareNum;
    /**
     * 评论数
     */
    @TableField("comment_num")
    private Long commentNum;
    /**
     * 发布时间
     */
    @TableField("pub_time")
    private Date pubTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthorIp() {
        return authorIp;
    }

    public void setAuthorIp(String authorIp) {
        this.authorIp = authorIp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getIndexFlag() {
        return indexFlag;
    }

    public void setIndexFlag(Integer indexFlag) {
        this.indexFlag = indexFlag;
    }

    public Integer getHotFlag() {
        return hotFlag;
    }

    public void setHotFlag(Integer hotFlag) {
        this.hotFlag = hotFlag;
    }

    public Integer getHotRank() {
        return hotRank;
    }

    public void setHotRank(Integer hotRank) {
        this.hotRank = hotRank;
    }

    public Integer getOriginalFlag() {
        return originalFlag;
    }

    public void setOriginalFlag(Integer originalFlag) {
        this.originalFlag = originalFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPublicityFlag() {
        return publicityFlag;
    }

    public void setPublicityFlag(Integer publicityFlag) {
        this.publicityFlag = publicityFlag;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Long getVirtualreadNum() {
        return virtualreadNum;
    }

    public void setVirtualreadNum(Long virtualreadNum) {
        this.virtualreadNum = virtualreadNum;
    }

    public Long getRealreadNum() {
        return realreadNum;
    }

    public void setRealreadNum(Long realreadNum) {
        this.realreadNum = realreadNum;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getShareNum() {
        return shareNum;
    }

    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Article{" +
        "id=" + id +
        ", userId=" + userId +
        ", authorIp=" + authorIp +
        ", author=" + author +
        ", columnId=" + columnId +
        ", indexFlag=" + indexFlag +
        ", hotFlag=" + hotFlag +
        ", hotRank=" + hotRank +
        ", originalFlag=" + originalFlag +
        ", status=" + status +
        ", publicityFlag=" + publicityFlag +
        ", source=" + source +
        ", abstracts=" + abstracts +
        ", title=" + title +
        ", shortTitle=" + shortTitle +
        ", content=" + content +
        ", picPath=" + picPath +
        ", virtualreadNum=" + virtualreadNum +
        ", realreadNum=" + realreadNum +
        ", likeNum=" + likeNum +
        ", shareNum=" + shareNum +
        ", commentNum=" + commentNum +
        ", pubTime=" + pubTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
