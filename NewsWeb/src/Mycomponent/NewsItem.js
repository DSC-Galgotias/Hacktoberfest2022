import PropTypes from 'prop-types'
import React, { Component } from 'react'

export class NewsItem extends Component {
    static propTypes = {}

    render() {
        let {title, description, imgurl,newsurl} = this.props
        return (
            <>
                <div className="card" style={{width: "18rem"}}>
                    <img src={!imgurl?"https://assets.entrepreneur.com/content/3x2/2000/1658521588-Ent-AutoWriter.jpeg":imgurl} className="card-img-top" alt="..."/>
                        <div className="card-body">
                            <h5 className="card-title">{title}</h5>
                            <p className="card-text">{description}</p>
                            <a href={newsurl} className="btn btn-primary">Read more</a>
                        </div>
                </div>
            </>
        )
    }
}

export default NewsItem