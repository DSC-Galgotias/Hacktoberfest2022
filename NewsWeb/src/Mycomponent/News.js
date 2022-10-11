import PropTypes from 'prop-types'
import React, { Component } from 'react'
import NewsItem from './NewsItem'

export class News extends Component {

    articles = [
        {
            "author": "mvolpe.hubspot@gmail.com (Mike Volpe)",
            "title": "SEO Step-by-Step Tutorial: 8 Easy Basics for Beginners to Master",
            "description": "If you’re just getting started with search engine optimization (SEO), then a step-by-step SEO tutorial is in order. By this point, you’ve likely heard of a few basic terms, such as keyword research and on-page optimization. But how do you apply all the knowle…",
            "url": "https://blog.hubspot.com/blog/tabid/6307/bid/1436/shortest-tutorial-ever-on-seo-search-engine-optimization.aspx#article",
            "urlToImage": "https://blog.hubspot.com/hubfs/seo-step-by-step-tutorial.jpeg#keepProtocol",
            "publishedAt": "2022-07-21T11:00:00Z",
            "content": "If youre just getting started with search engine optimization (SEO), then a step-by-step SEO tutorial is in order. By this point, youve likely heard of a few basic terms, such as keyword research and… [+7572 chars]"
        },
        {
           
            "author": "Laura Stupple",
            "title": "7 SEO Copywriting Tips to Get Your Business Ranking on Google",
            "description": "Want to learn how to land on page one of Google and other search engines? SEO copywriting could be your secret weapon.",
            "url": "https://www.entrepreneur.com/article/431025",
            "urlToImage": "https://assets.entrepreneur.com/content/3x2/2000/1659046027-shutterstock-1187034616.jpg",
            "publishedAt": "2022-08-03T14:00:00Z",
            "content": "Search engine optimization (SEO) is important for any business wanting to build an online presence and sell online.\r\nWhen we write engaging SEO content, Google rewards us by placing web pages higher … [+6845 chars]"
        },
        {
           
            "author": "Timothy Carter",
            "title": "SEO Isn't Just About Link Building. Don't Overlook These Expert Strategies.",
            "description": "While link building is a powerful SEO tactic, it cannot boost your ranking without these other expert-backed strategies.",
            "url": "https://www.entrepreneur.com/article/423753",
            "urlToImage": "https://assets.entrepreneur.com/content/3x2/2000/1656359919-shutterstock-1157182387copy.jpg",
            "publishedAt": "2022-07-06T11:00:00Z",
            "content": "Human beings like to think simplistically. We like to neatly categorize concepts and see things in black-and-white. We like to ignore nuance for the sake of a one-sentence summary. And when it comes … [+5012 chars]"
        },
        {
           
            "author": "Entrepreneur Store",
            "title": "This $40 Tool Can Overhaul Your Content Production",
            "description": "Create more content, faster.",
            "url": "https://www.entrepreneur.com/article/432000",
            "urlToImage": "https://assets.entrepreneur.com/content/3x2/2000/1658521588-Ent-AutoWriter.jpeg",
            "publishedAt": "2022-07-28T12:30:00Z",
            "content": "Copywriting may sound easy. It's just talking about your product, right? Well, it's a lot more complicated than that. Even if you follow the commandments of great copywriting, you're likely missing o… [+1874 chars]"
        },
        {
            
            "author": "Reuters Fact Check",
            "title": "Fact Check-Headline of Insider article altered to show fake Musk response to Trump - Reuters",
            "description": "A digitally altered screenshot of an Insider article about former U.S. president Donald Trump and Tesla Inc. CEO Elon Musk is circulating on social media.",
            "url": "https://www.reuters.com/article/factcheck-insider-altered-idUSL1N2YV2AG",
            "urlToImage": "https://s1.reutersmedia.net/resources_v2/images/rcom-default.png?w=800",
            "publishedAt": "2022-07-14T18:53:00Z",
            "content": "A digitally altered screenshot of an Insider article about former U.S. president Donald Trump and Tesla Inc. CEO Elon Musk is circulating on social media.\r\nThe altered screenshot refers to a message … [+2432 chars]"
        },
        {
            
            "author": "Reuters Fact Check",
            "title": "Fact Check-False arrest reports of Tim Hortons executive stem from satirical website - Reuters",
            "description": "Daniel Schwartz, co-chairman at Tim Hortons parent company Restaurant Brands International, was not arrested on July 20, contrary to claims online. The allegation originated on a website that describes itself as the “most trusted source of satire on the West …",
            "url": "https://www.reuters.com/article/factcheck-timhortons-fake-idUSL1N2Z315Q",
            "urlToImage": "https://s1.reutersmedia.net/resources_v2/images/rcom-default.png?w=800",
            "publishedAt": "2022-07-22T13:55:00Z",
            "content": "Daniel Schwartz, co-chairman at Tim Hortons parent company Restaurant Brands International, was not arrested on July 20, contrary to claims online. The allegation originated on a website that describ… [+2221 chars]"
        },
    ]
    constructor(){
        super();
        this.state = {
            articles: [],
            loading: false,
            page: 1
        }
    }

    async componentDidMount(){
        let url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=3b5abb8ac7c3453d88e3d2636afbc5e3&page=1";
        let data = await fetch(url);
        let parsedData = await data.json()
        this.setState({articles: parsedData.articles})
    }

    handlenextclick = () => {

    }
    handleprevclick = () => {

    }

    render() {
        return (
            <>
                <div className='container my-3'>
                    <h2 className='text-center'>NewsWeb - Top Headlines</h2>
                    <div className='row'>
                        {this.state.articles.map((element)=>{
                            return(
                            <div className='col-md-4' key={element.url}>
                            <NewsItem title={element.title} imgurl={element.urlToImage} description={element.description} newsurl={element.url}/>
                        </div>
                            )
                        })}
                       

                    </div>
                </div>
                <div className='container d-flex justify-content-between'>
                <button type="button" disabled={this.state.page>=1} class="btn btn-dark" onClick={this.handleprevclick}>&larr; Previous</button>
                <button type="button" class="btn btn-dark" onClick={this.handlenextclick}> Next &rarr;</button>
                </div>
            </>
        )
    }
}

export default News