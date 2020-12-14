package com.wuda.foundation.core.commons;

import com.wuda.foundation.lang.AlreadyExistsException;
import com.wuda.foundation.lang.CreateMode;
import com.wuda.foundation.lang.CreateResult;
import com.wuda.foundation.lang.RelatedDataExists;
import com.wuda.foundation.lang.tree.Tree;

import java.util.List;

/**
 * 树形结构管理.
 * 很多术语参考<a href="http://www.cs.columbia.edu/~allen/S14/NOTES/trees.pdf">trees</a>
 *
 * @param <C> 用于创建节点的参数的类型
 * @param <U> 用于更新节点的参数的类型
 * @param <D> 用于描述节点的类型
 * @author wuda
 * @since 1.0.3
 */
public interface TreeManager<C extends CreateTreeNode, U extends UpdateTreeNode, D extends DescribeTreeNode> {

    /**
     * 创建一个节点.
     *
     * @param createTreeNode 新增节点的参数
     * @param createMode     create mode
     * @param opUserId       操作者用户ID
     * @return 创建结果
     * @throws AlreadyExistsException       比如节点名称在同一个父节点下已经存在(同一个父节点下不允许存在同名的子节点)
     * @throws ParentNodeNotExistsException 父节点不存在
     */
    CreateResult createTreeNode(C createTreeNode, CreateMode createMode, Long opUserId) throws AlreadyExistsException, ParentNodeNotExistsException;

    /**
     * 更新节点.
     *
     * @param updateTreeNode 更新参数
     * @param opUserId       操作者用户ID
     * @throws AlreadyExistsException 比如更新后的节点名称在同一个父节点下已经存在(同一个父节点下不允许存在同名的子节点)
     */
    void updateNode(U updateTreeNode, Long opUserId) throws AlreadyExistsException;

    /**
     * 删除节点.
     *
     * @param nodeId   准备删除的节点ID
     * @param opUserId 操作者用户ID
     * @throws RelatedDataExists 如果该节点下还有子节点存在
     */
    void deleteTreeNode(Long nodeId, Long opUserId) throws RelatedDataExists;

    /**
     * 统计节点的child的数量.
     *
     * @param nodeId node id
     * @return child的数量
     */
    int childCount(Long nodeId);

    /**
     * 获取给定节点的所有后代.
     *
     * @param nodeId 节点ID
     * @return 所有后代
     */
    List<D> getDescendant(Long nodeId);

    /**
     * 获取给定节点的所有祖先.
     *
     * @param nodeId 节点ID
     * @return 所有祖先
     */
    List<D> getAncestor(Long nodeId);

    /**
     * 获取给定节点的所有子女(不包含孙子及更后的后代)节点.
     *
     * @param nodeId 节点ID
     * @return 所有children
     */
    List<D> getChildren(Long nodeId);

    /**
     * 获取节点信息.
     *
     * @param nodeId node id
     * @return 节点信息
     */
    D getTreeNode(Long nodeId);

    /**
     * 判断正在创建的节点是否root节点.
     *
     * @param createTreeNode 创建节点的参数
     * @return <code>true</code>-如果是
     */
    boolean isCreatingRootTreeNode(CreateTreeNode createTreeNode);

    /**
     * 判断节点是否root节点.
     *
     * @param describeTreeNode 节点
     * @return <code>true</code>-如果是
     */
    boolean isRootTreeNode(DescribeTreeNode describeTreeNode);

    /**
     * 获取root节点的所有后裔.
     * 比如,在【tree-like】的表中,id/parentId已经可以表示树结构,但是为了方便,可以再加一个rootId字段,这样可以很快的查询出一棵完整的树,
     * 因此,对于【tree-like】的表的实现类,该方法的作用就是根据rootId查询记录,比如
     * <pre>
     *     select * from item_category where root_id = 5
     * </pre>
     *
     * @param rootId root id
     * @return root节点的所有后裔, 如果给定的ID并不是root节点的ID, 则不会有节点返回, 可能为<code>null</code>,也可能为空集合
     */
    List<D> getDescendantOfRoot(Long rootId);

    /**
     * 在给定的父节点下是否已经存在指定名称的节点.
     *
     * @param parentId  parent id
     * @param childName 子节点名称
     * @return <code>true</code>-如果存在
     */
    boolean checkNameExists(Long parentId, String childName);

    /**
     * 获取任意节点所在的树.
     *
     * @param nodeId 任意的节点ID
     * @return 该节点所在的树
     */
    Tree<Long, D> getTree(Long nodeId);

}
