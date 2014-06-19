package org.bananaLaba.shaderModel.template;
// TODO: get rid of self-acting template units:
// 1) use a tree structure without node id's but with different node content types:
//      1.1) tree iterator modifier for inner nodes;
//      1.2) static and reference unit chain for leaf nodes;
// 2) for fragment templates it would be convenient to leave the same structure but its nodes will contain tree
// structures mentioned above.
// 3) the tree iterators mentioned above should contain template context in order to persist variables used, for
// example, by a loop body.
